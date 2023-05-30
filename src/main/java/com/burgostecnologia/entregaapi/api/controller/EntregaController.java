package com.burgostecnologia.entregaapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.burgostecnologia.entregaapi.domain.model.Entrega;
import com.burgostecnologia.entregaapi.domain.service.EntregaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega){
        return entregaService.solicitarEntregar(entrega);
    }

    
}
