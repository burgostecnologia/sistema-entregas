package com.burgostecnologia.entregaapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice   //notação para trata exceção de qualquer controller
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource; //tratativa de mensagem personalizadas por critica no messages.properties

    //se olhar no log ao usar o ResponseEntityExceptionHandler aparece como resolved por ele e resume a exceção,
    //o metodo handleMethodArgumentNotValid é usado, então podemos sobrescrever ele para tratar melhor o erro
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<Problema.CampoProblema> campos = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String nome = ((FieldError) error).getField();
            //String mensagem = error.getDefaultMessage();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new Problema.CampoProblema(nome,mensagem));
        }
                
        Problema problema = new Problema();   
        problema.setStatus(status.value());
        problema.setDataHora(LocalDateTime.now());            
        problema.setTitulo("Um ou mais campos estão inválidos, preencha corretamente");
        problema.setCampos(campos);

        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
        return handleExceptionInternal(ex, problema, headers, status, request);
    }

 
}
