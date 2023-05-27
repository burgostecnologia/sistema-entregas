package com.burgostecnologia.entregaapi.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problema {
    
private Integer status;
private LocalDateTime dataHora;
private String titulo;
private List<CampoProblema> campos;

@AllArgsConstructor
@Getter
@Setter
public static class CampoProblema {
    private String nome;
    private String mensagem;
}

}
