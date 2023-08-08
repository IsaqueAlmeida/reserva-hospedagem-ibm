package com.example.reservahospedagemibm.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Reserva {

    // Identificador principal (ID) da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Anotação de não nulo para o nome do hóspede
    @NonNull
    private String nomeHospede;

    // Data de início da reserva
    private LocalDate dataInicio;

    // Data de término da reserva
    private LocalDate dataFim;

    // Número de pessoas para a reserva
    private Integer quantidadeDePessoas;

    // Status da reserva (por exemplo, CONFIRMADA, PENDENTE)
    private String status;

    // Construtor para criar uma instância de Reserva
    public Reserva(
            Integer id,
            @NonNull String nomeHospede,
            LocalDate dataInicio,
            LocalDate dataFim,
            Integer quantidadeDePessoas,
            String status
    ){
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.quantidadeDePessoas = quantidadeDePessoas;
        this.status = status;
    }

    // Métodos getter e setter para id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Métodos getter e setter para nomeHospede
    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    // Métodos getter e setter para dataInicio
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    // Métodos getter e setter para dataFim
    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    // Métodos getter e setter para quantidadeDePessoas
    public Integer getQuantidadeDePessoas() {
        return quantidadeDePessoas;
    }

    public void setQuantidadeDePessoas(Integer quantidadeDePessoas) {
        this.quantidadeDePessoas = quantidadeDePessoas;
    }

    // Métodos getter e setter para status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
