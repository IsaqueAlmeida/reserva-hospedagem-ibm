package com.example.reservahospedagemibm.service;

import com.example.reservahospedagemibm.controller.ReservaController;
import com.example.reservahospedagemibm.domain.Reserva;
import com.example.reservahospedagemibm.respository.ReservaRepository;
import com.example.reservahospedagemibm.service.exception.ReservaNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Classe de testes para o serviço ReservaService.
 */
@SpringBootTest
public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    /**
     * Teste para inserir uma reserva.
     */
    @Test
    public void testInsertReserva() {

        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        Reserva reserva = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");

        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        Reserva reservaInserido = reservaService.insert(reserva);

        assertNotNull(reservaInserido);
        assertEquals(reserva.getNomeHospede(), reservaInserido.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaInserido.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaInserido.getDataFim());
        assertEquals(reserva.getQuantidadeDePessoas(), reservaInserido.getQuantidadeDePessoas());
        assertEquals(reserva.getStatus(), reservaInserido.getStatus());

        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    /**
     * Teste para buscar uma reserva pelo ID quando existe.
     */
    @Test
    public void testFindReservaByIdExistente() {
        Integer reservaId = 1;
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        Reserva reserva = new Reserva(reservaId,  "João", dataInicio, dataFim, 1, "CONFIRMADA");

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));

        Reserva reservaEncontrado = reservaService.findById(reservaId);
        assertNotNull(reservaEncontrado);
        assertEquals(reserva.getId(), reservaEncontrado.getId());
        assertEquals(reserva.getNomeHospede(), reservaEncontrado.getNomeHospede());
        assertEquals(reserva.getDataInicio(), reservaEncontrado.getDataInicio());
        assertEquals(reserva.getDataFim(), reservaEncontrado.getDataFim());
        assertEquals(reserva.getStatus(), reservaEncontrado.getStatus());

        verify(reservaRepository, times(1)).findById(reservaId);
    }

    /**
     * Teste para buscar uma reserva pelo ID quando não existe.
     */
    @Test
    public void testFindReservaByIdNaoExistente() {
        Integer reservaId = 99;

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.empty());
        assertThrows(ReservaNotFoundException.class, () -> reservaService.findById(reservaId));
        verify(reservaRepository, times(1)).findById(reservaId);
    }

    /**
     * Teste para obter todas as reservas.
     */
    @Test
    public void testGetAllReservas() {
        LocalDate dataInicio = LocalDate.of(2023, 8, 15);
        LocalDate dataFim = LocalDate.of(2023, 8, 20);
        Reserva reserva1 = new Reserva(1, "João", dataInicio, dataFim, 1, "CONFIRMADA");

        LocalDate dataInicio2 = LocalDate.of(2023, 8, 22);
        LocalDate dataFim2 = LocalDate.of(2023, 8, 25);
        Reserva reserva2 = new Reserva(2, "Maria", dataInicio2, dataFim2, 2, "PENDENTE");

        List<Reserva> reservas = Arrays.asList(reserva1, reserva2);
        when(reservaService.getAllReservas()).thenReturn(reservas);

        List<Reserva> result = reservaService.getAllReservas();

        assertEquals(2, result.size());
        assertEquals(reserva1, result.get(0));
        assertEquals(reserva2, result.get(1));

        verify(reservaRepository).findAll();
    }

    /**
     * Teste para atualizar uma reserva.
     */
    @Test
    public void testUpdateReserva() {
        Integer reservaId = 1;
        Integer reservaId2 = 2;

        LocalDate dataInicio = LocalDate.of(2023, 8, 22);
        LocalDate dataFim = LocalDate.of(2023, 8, 27);
        Reserva reservaAtualizado = new Reserva(reservaId, "Pedro", dataInicio, dataFim, 2, "PENDENTE");

        LocalDate dataInicio2 = LocalDate.of(2023, 8, 22);
        LocalDate dataFim2 = LocalDate.of(2023, 8, 25);
        Reserva reservaExistente = new Reserva(reservaId2, "Maria", dataInicio2, dataFim2, 2, "CONFIRMADA");

        when(reservaRepository.findById(reservaId2)).thenReturn(Optional.of(reservaExistente));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reservaAtualizado);

        Reserva result = reservaService.update(reservaAtualizado);

        assertNotNull(result);
        assertEquals(reservaId, result.getId());
        assertEquals(reservaAtualizado.getNomeHospede(), result.getNomeHospede());
        assertEquals(reservaAtualizado.getDataInicio(), result.getDataInicio());
        assertEquals(reservaAtualizado.getDataFim(), result.getDataFim());
        assertEquals(reservaAtualizado.getQuantidadeDePessoas(), result.getQuantidadeDePessoas());
        assertEquals(reservaAtualizado.getStatus(), result.getStatus());

        verify(reservaRepository, times(1)).findById(reservaId);
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    /**
     * Teste para deletar uma reserva.
     */
    @Test
    public void testDeleteReserva() {
        Integer reservaId = 1;
        reservaService.deleteReserva(reservaId);
        verify(reservaRepository, times(1)).deleteById(reservaId);
    }
}
