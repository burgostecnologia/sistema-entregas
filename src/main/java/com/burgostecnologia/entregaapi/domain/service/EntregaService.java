package com.burgostecnologia.entregaapi.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.burgostecnologia.entregaapi.domain.exceptionhandler.NegocioException;
import com.burgostecnologia.entregaapi.domain.model.Cliente;
import com.burgostecnologia.entregaapi.domain.model.Entrega;
import com.burgostecnologia.entregaapi.domain.model.StatusEntrega;
import com.burgostecnologia.entregaapi.domain.repository.ClienteRepository;
import com.burgostecnologia.entregaapi.domain.repository.EntregaRepository;

@Service
public class EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitarEntregar(Entrega entrega){

        //clienteRepository.findById(entrega.getCliente().getId()) - retorna um optional de Cliente (container que pode ou não ter alguma coisa)
        // .orElseThrow pega o que esta no optional, que no caso é o objeto Cliente ai pode ser atribuido a uma variavel do tipo Cliente
        // .orElseThrow se não tiver nada no Optinal o parametro dentro será uma exceção lançada atraves de uma lambda
        
        //refatorei esse codigo e coloquei na classe ServiceCliente caso queira reaproveitar chamada 
        //Cliente cliente = clienteRepository.findById(entrega.getCliente().getId()).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
        
        Cliente cliente = clienteService.consultarClienteExistente(entrega.getCliente().getId());

        
        // se não fizaer isso o demais campos além do id retornam nulo pq no bodyrequest so é enviado o id do cliente, e como ja recuperamos o cliente acima podemos atualizar os campos com o que tem na base
        entrega.setCliente(cliente);


        //regra de negocio para campos que não vem da requisição
        entrega.setStatusEntrega(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        
        return  entregaRepository.save(entrega);
    }
    

}
