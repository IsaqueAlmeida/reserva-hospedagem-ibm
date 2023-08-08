package com.example.reservahospedagemibm.controller;

import com.example.reservahospedagemibm.domain.Reserva;
import com.example.reservahospedagemibm.dto.ReservaDto;
import com.example.reservahospedagemibm.service.ReservaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de testes para o controlador ReservaController.
 */
@WebMvcTest(ReservaController.class)
public class ReservaControllerTest {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService reservaService;

    @InjectMocks
    private ReservaController reservaController;

    private ReservaDto reservaDto;

    // (método setup())
    @BeforeEach
    public void setup(){
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        reservaDto = new ReservaDto(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");
    }

    /**
     * Teste para a inserção de uma reserva.
     */
    @Test
    public void testInsertReserva() throws Exception {
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        Reserva reservaInserido = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");

        when(reservaService.insert(any(Reserva.class))).thenReturn(reservaInserido);

        mockMvc.perform(MockMvcRequestBuilders.post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeHospede\":\"João\",\"dataInicio\":\"2023-08-15\",\"dataFim\":\"2023-08-20\",\"quantidadeDePessoas\":1,\"status\":\"CONFIRMADA\"}"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/reservas/1"));
    }

    /**
     * Teste para buscar uma reserva pelo ID quando existente.
     */
    @Test
    public void testFindReservaByIdExistente() throws Exception {
        Integer reservaId = 1;

//        LocalDate dataInicio = LocalDate.parse("15/08/2023", dateTimeFormatter);
//        LocalDate dataFim = LocalDate.parse("20/08/2023", dateTimeFormatter);
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);

        Reserva reservaEncontrado = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");

        when(reservaService.findById(reservaId)).thenReturn(reservaEncontrado);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nomeHospede").value("João"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value(dataInicio.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataInicio").value(dataInicio.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantidadeDePessoas").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("CONFIRMADA"));
    }

    /**
     * Teste para obter todas as reservas.
     */
    @Test
    public void testGetAllReserva() throws Exception {
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        Reserva reserva1 = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");


        LocalDate dataInicio2 = LocalDate.of(2023, 8, 22);
        LocalDate dataFim2 = LocalDate.of(2023, 8, 25);
        Reserva reserva2 = new Reserva(2, "Maria", dataInicio2, dataFim2, 2, "PENDENTE");

        List<Reserva> reservas = Arrays.asList(reserva1, reserva2);

        when(reservaService.getAllReservas()).thenReturn(reservas);

        mockMvc.perform(MockMvcRequestBuilders.get("/reservas/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nomeHospede").value("João"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dataInicio").value(dataInicio.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dataFim").value(dataFim.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantidadeDePessoas").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("CONFIRMADA"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nomeHospede").value("Maria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dataInicio").value(dataInicio2.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].dataFim").value(dataFim2.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantidadeDePessoas").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].status").value("PENDENTE"));

    }

    /**
     * Teste para atualizar uma reserva.
     */
    @Test
    public void testUpdateReserva() throws Exception {
//        LocalDate dataInicio = LocalDate.parse("22/08/2023", dateTimeFormatter);
//        LocalDate dataFim = LocalDate.parse("25/08/2023", dateTimeFormatter);
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);

        ReservaDto reservaDto = new ReservaDto(1, "João", dataInicio, dataFim, 1, "PENDENTE");

        Reserva reservaUpdate = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");

        when(reservaService.fromDto(reservaDto)).thenReturn(reservaUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/reservas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nomeHospede\":\"João\",\"dataInicio\":\"2023-08-15\",\"dataFim\":\"2023-08-20\",\"quantidadeDePessoas\":1,\"status\":\"CONFIRMADA\"}"))
                .andExpect(status().isNoContent());

        verify(reservaService).update(reservaUpdate);
    }

    /**
     * Teste para deletar uma reserva.
     */
    @Test
    public void testDeleteReserva() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/reservas/1"))
                .andExpect(status().isNoContent());

        verify(reservaService).deleteReserva(1);
    }
}