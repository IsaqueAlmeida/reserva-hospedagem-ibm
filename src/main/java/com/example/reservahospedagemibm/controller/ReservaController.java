package com.example.reservahospedagemibm.controller;

import com.example.reservahospedagemibm.domain.Reserva;
import com.example.reservahospedagemibm.dto.ReservaDto;
import com.example.reservahospedagemibm.service.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Classe controladora que lida com as requisições relacionadas às reservas.
 *
 * A anotação @RestController combina as anotações @Controller e @ResponseBody,
 * indicando que os métodos desta classe retornam diretamente respostas JSON.
 * A anotação @RequestMapping define o mapeamento da URL base para as requisições
 * relacionadas às reservas.
 */
@RestController
@RequestMapping("/reservas")
@Controller
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    /**
     * Cria uma nova reserva.
     *
     * reserva: A reserva a ser inserida.
     * @return Uma resposta com status 201 (Created) e o cabeçalho de localização contendo a URL da reserva criada.
     */
    @PostMapping()
    public ResponseEntity<Void> insert(@Validated @RequestBody Reserva reserva) {
        Reserva reservaInserir = reservaService.insert(reserva);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reservaInserir.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * Busca uma reserva pelo ID.
     *
     * id: O ID da reserva a ser buscada.
     * @return Uma resposta com a reserva encontrada e status 200 (OK).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@Validated @PathVariable Integer id) {
        Reserva reservaId = reservaService.findById(id);
        return ResponseEntity.ok(reservaId);
    }

    /**
     * Retorna todas as reservas.
     *
     * @return Uma resposta com a lista de todas as reservas e status 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Reserva>> getAllReserva() {
        List<Reserva> reservasGetAll = reservaService.getAllReservas();
        return ResponseEntity.ok(reservasGetAll);
    }

    /**
     * Atualiza uma reserva existente.
     *
     * dto: Os novos dados da reserva.
     * id:  O ID da reserva a ser atualizada.
     * @return Uma resposta com status 204 (No Content) indicando que a atualização foi bem-sucedida.
     */
    @PutMapping("/reservas/{id}")
    public ResponseEntity<Void> update(@RequestBody ReservaDto dto, @Validated @PathVariable Integer id) {
        Reserva reservaUpdate = reservaService.fromDto(dto);
        reservaUpdate.setId(id);
        reservaService.update(reservaUpdate);
        return ResponseEntity.noContent().build();
    }

    /**
     * Deleta uma reserva pelo ID.
     *
     * id: O ID da reserva a ser excluída.
     * @return Uma resposta com status 204 (No Content) indicando que a exclusão foi bem-sucedida.
     */
    @DeleteMapping("/reservas/{id}/cancelar")
    public ResponseEntity<Void> deleteReserva(@Validated @PathVariable Integer id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
