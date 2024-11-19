package com.motoparts.excecoes;

import com.motoparts.excecoes.entidade.ErroObject;
import com.motoparts.excecoes.entidade.MensagemExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErroObject> erros = getErros(ex);
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Os seguintes campos estão inválidos", erros));

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleValidationErrors(HttpMessageNotReadableException ex) {
        return ResponseEntity.ok().body(new MensagemExceptionHandler(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "O valor fornecido para não é válido. Por favor, forneça um número decimal.", null));

    }



    private List<ErroObject> getErros(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().
                stream().map(erro -> new ErroObject(erro.getDefaultMessage(), erro.getField())).collect(Collectors.toList());
    }




}
