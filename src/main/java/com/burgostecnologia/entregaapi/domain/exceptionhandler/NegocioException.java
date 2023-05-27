package com.burgostecnologia.entregaapi.domain.exceptionhandler;

public class NegocioException extends RuntimeException{
    
    public NegocioException(String mensagem){
        super (mensagem);
    }

}
