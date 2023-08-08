package com.example.reservahospedagemibm.controller.exceptions;

import com.example.reservahospedagemibm.service.exception.ReservaNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    // Injeta uma instância HttpServletRequest para acesso a informações da requisição
    private final HttpServletRequest request;

    // Construtor para injetar a instância de HttpServletRequest
    public ControllerExceptionHandler(HttpServletRequest request) {
        this.request = request;
    }

    // Manipula exceções do tipo ReservaNotFoundException
    @ExceptionHandler(ReservaNotFoundException.class)
    public ResponseEntity<StandartError> reservaNotFound(ReservaNotFoundException e) {
        // Cria um objeto StandartError contendo informações sobre o erro
        StandartError error = new StandartError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());

        // Retorna uma resposta com o código de status NOT_FOUND e o objeto de erro
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
