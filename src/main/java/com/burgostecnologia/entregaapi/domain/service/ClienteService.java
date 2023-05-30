package com.burgostecnologia.entregaapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.burgostecnologia.entregaapi.domain.exceptionhandler.NegocioException;
import com.burgostecnologia.entregaapi.domain.model.Cliente;
import com.burgostecnologia.entregaapi.domain.repository.ClienteRepository;

import jakarta.transaction.Transactional;

//regras de negocio sempre  no Service, podendo criar exceções para consultas sendo feitas no repository diretamente pelo controller(caso não tenha regra de negocio envolvida)
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional //garante tudo ou nada em uma transação 
    public Cliente salvarCliente(Cliente cliente){

        boolean emailEmUSo = clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExiste -> !clienteExiste.equals(cliente));
        if (emailEmUSo){
            throw new NegocioException("Já existe um usuário com esse e-mail"); //dispara a exception mas é tartada na  exceptionhandler da api
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluirCliente(Long idCliente){
        clienteRepository.deleteById(idCliente);
    }

    public Cliente consultarClienteExistente(Long idCliente){
        return clienteRepository.findById(idCliente).orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

}
