package com.example.reservahospedagemibm.dto;

import com.example.reservahospedagemibm.domain.Reserva;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ReservaDto implements Serializable {

    private Integer id; // Identificador da reserva

    private String nomeHospede; // Nome do hóspede

    private LocalDate dataInicio; // Data de início da estadia

    private LocalDate dataFim; // Data de término da estadia

    private Integer quantidadeDePessoas; // Quantidade de pessoas na reserva

    private String status; // Status da reserva (CONFIRMADA, PENDENTE, CANCELADA)

    // Construtor com todos os campos
    public ReservaDto(Integer id, String nomeHospede, LocalDate dataInicio, LocalDate dataFim, Integer quantidadeDePessoas, String status) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.status = status;
    }

    // Construtor a partir de uma entidade Reserva
    public ReservaDto(Reserva reservaDto) {
        id = reservaDto.getId();
        nomeHospede = reservaDto.getNomeHospede();
        dataInicio = reservaDto.getDataInicio();
        dataFim = reservaDto.getDataFim();
        quantidadeDePessoas = reservaDto.getQuantidadeDePessoas();
        status = reservaDto.getStatus();
    }
}
