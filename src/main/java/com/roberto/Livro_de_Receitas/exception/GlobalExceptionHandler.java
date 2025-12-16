package com.roberto.Livro_de_Receitas.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice //ANOTAÇÃO QUE FICA ESCUTANDO PARA RETORNAR OS EXCEPTIONS
public class GlobalExceptionHandler {

    //CLASSE PARA TRATAR AS EXCEÇÕES GLOBAIS
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Object> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        Map<String, Object> body = new LinkedHashMap<>();

        //OS BODY É O QUE VAI RETORNAR NO JSON QUANDO NÃO ENCONTRAR
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "ID não encontrado");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    //CLASSE GENÉRICA PARA TRATAR EXCEÇÕES INTERNAS DO SERVIDOR
    @ExceptionHandler(Exception.class) //CRIA UMA ANOTAÇÃO PARA A CLASSE EXCEPTION
    public ResponseEntity<Object> handleGenericException(Exception ex){
        Map<String, Object> body = new LinkedHashMap<>();

        //OS BODY É O QUE VAI RETORNAR NO JSON QUANDO NÃO ENCONTRAR
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Recurso não encontrado");
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
