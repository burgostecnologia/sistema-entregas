package com.burgostecnologia.entregaapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.burgostecnologia.entregaapi.domain.model.Entrega;
import com.burgostecnologia.entregaapi.domain.repository.EntregaRepository;
import com.burgostecnologia.entregaapi.domain.service.EntregaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @Autowired
    private EntregaRepository entregaRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitarEntrega(@Valid @RequestBody Entrega entrega){
        return entregaService.solicitarEntregar(entrega);
    }

    @GetMapping
    public List<Entrega> buscarEntregas(){
        return entregaRepository.findAll();
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<Entrega> buscarEntregaEspecifica(@PathVariable Long entregaId){
        Optional<Entrega> entrega = entregaRepository.findById(entregaId);
        if(entrega.isPresent()){
            return ResponseEntity.ok(entrega.get());
        }
        return ResponseEntity.notFound().build();
    }



    
}
