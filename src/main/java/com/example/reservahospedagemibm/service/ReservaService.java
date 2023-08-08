package com.example.reservahospedagemibm.service;

import com.example.reservahospedagemibm.domain.Reserva;
import com.example.reservahospedagemibm.dto.ReservaDto;
import com.example.reservahospedagemibm.respository.ReservaRepository;
import com.example.reservahospedagemibm.service.exception.ReservaNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço que lida com a lógica de negócios relacionada às reservas.
 *
 * A anotação @Service indica que esta classe é um componente de serviço gerenciado pelo Spring.
 * Ela encapsula a lógica de negócios associada às operações de reserva.
 */
@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    /**
     * Insere uma nova reserva.
     *
     * reserva: A reserva a ser inserida.
     * @return: reserva inserida.
     */
    public Reserva insert(Reserva reserva) {
        reserva.setId(null);
        return reservaRepository.save(reserva);
    }

    /**
     * Busca uma reserva por ID.
     *
     * id: O ID da reserva a ser buscada.
     * @return: A reserva encontrada.
     * @throws ReservaNotFoundException: Se a reserva não for encontrada.
     */
    public Reserva findById(Integer id) {
        Optional<Reserva> reservaId = reservaRepository.findById(id);

        return reservaId.orElseThrow(() -> new ReservaNotFoundException("Reserva não encontrada com o ID: " + id));
    }

    /**
     * Retorna uma lista de todas as reservas.
     *
     * @return Uma lista de reservas.
     */
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    /**
     * Atualiza os dados de uma reserva existente.
     *
     * reservaUpdate: Os novos dados da reserva.
     * @return: A reserva atualizada.
     */
    public Reserva update(Reserva reservaUpdate) {
        Reserva reserva = findById(reservaUpdate.getId());
        updateData(reserva, reservaUpdate);
        return reservaRepository.save(reserva);
    }

    /**
     * Atualiza os dados de uma reserva existente com os novos dados.
     *
     * nova:  A reserva existente a ser atualizada.
     * antiga: Os novos dados da reserva.
     */
    private void updateData(Reserva nova, Reserva antiga) {
        nova.setNomeHospede(antiga.getNomeHospede());
        nova.setQuantidadeDePessoas(antiga.getQuantidadeDePessoas());
    }

    /**
     * Exclui uma reserva por ID.
     *
     * id: O ID da reserva a ser excluída.
     */
    public void deleteReserva(Integer id) {
        reservaRepository.deleteById(id);
    }

    /**
     * Converte um DTO de reserva em uma instância de Reserva.
     *
     * reservaDto: O DTO de reserva.
     * @return: A instância de Reserva correspondente ao DTO.
     */
    public Reserva fromDto(ReservaDto reservaDto) {
        return new Reserva(
                reservaDto.getId(),
                reservaDto.getNomeHospede(),
                reservaDto.getDataInicio(),
                reservaDto.getDataFim(),
                reservaDto.getQuantidadeDePessoas(),
                reservaDto.getStatus()
        );
    }
}
