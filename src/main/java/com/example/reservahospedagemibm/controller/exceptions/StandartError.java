package com.example.reservahospedagemibm.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa um objeto de erro padrão para respostas de exceções.
 * Contém informações sobre o status HTTP, horário e mensagens de erro.
 */
@NoArgsConstructor
@Getter
@Setter
public class StandartError {

    /**
     * O status HTTP da resposta de erro.
     */
    private Integer status;

    /**
     * O horário em que o erro ocorreu, em milissegundos desde a época.
     */
    private Long horario;

    /**
     * Uma mensagem de erro descritiva ou uma lista de mensagens de erro.
     */
    private String mensagens;

    /**
     * Construtor para criar um objeto de erro padrão com informações específicas.
     *
     * status: O status HTTP da resposta de erro.
     * horario: O horário em que o erro ocorreu, em milissegundos desde a época.
     * mensagens: Uma mensagem de erro descritiva ou uma lista de mensagens de erro.
     */
    public StandartError(Integer status, Long horario, String mensagens) {
        this.status = status;
        this.horario = horario;
        this.mensagens = mensagens;
    }
}
