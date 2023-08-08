package com.example.reservahospedagemibm.service.exception;

/**
 * Exceção personalizada para indicar que uma reserva não foi encontrada.
 * Esta exceção é lançada quando uma busca por ID de reserva não retorna resultados.
 */
public class ReservaNotFoundException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * A mensagem de erro que descreve o motivo da exceção.
     */
    public ReservaNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor que aceita uma mensagem de erro e uma causa subjacente.
     *
     * mensagem de erro que descreve o motivo da exceção.
     * A causa subjacente da exceção.
     */
    public ReservaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
