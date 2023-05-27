package com.burgostecnologia.entregaapi.api.controller;

import java.lang.annotation.Retention;
// import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.burgostecnologia.entregaapi.domain.model.Cliente;
import com.burgostecnologia.entregaapi.domain.repository.ClienteRepository;

import jakarta.validation.Valid;

// import jakarta.persistence.EntityManager;
// import jakarta.persistence.PersistenceContext;
//import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    // @PersistenceContext
    // private EntityManager manager;

    @Autowired
    private ClienteRepository clienteRepository;

    //opcao 1 listarCliente por ID sem tratar retorno de status
    // @GetMapping("/{clienteId}")
    // public Cliente listarCliente(@PathVariable Long clienteId){

    //      Optional<Cliente> cliente = clienteRepository.findById(clienteId); // Optional container que pode retornar ou não algo.
    //      return cliente.orElse(null); //o orElse foi usado pq nao da pra retornar um optional precisa retornar o objeto cliente; O orElse retorno o conteudo que tiver, no caso um objeto cliente, caso nao tenha nada retorna o parametro, no caso null.
    // }

    //opcao 2 listarCliente por ID tratando retorno de status
    //200 - quando existe
    //404 - quando nao existe 
    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> listarCliente(@PathVariable Long idCliente){          // <> = onde fica o type parameter / ResponseEntity pode manipular cabecalho e status
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if(cliente.isPresent()){ //se contem algo no conteiner optional
            return ResponseEntity.ok(cliente.get()); // ok = 200
        }
        return ResponseEntity.notFound().build(); //  notFound = 404 precisa do build para construir um retorno vazio
        
    }

    //opcao 3 listarCliente por ID tratando retorno de status - simplificando 1
    //200 - quando existe
    // //404 - quando nao existe 
    // @GetMapping("/{idCliente}")
    // public ResponseEntity<Cliente> listarCliente(@PathVariable Long idCliente){          // <> = onde fica o type parameter / ResponseEntity pode manipular cabecalho e status
    //     return clienteRepository.findById(idCliente)
    //     .map(cliente -> ResponseEntity.ok(cliente))  //lambda expression
    //     .orElse(ResponseEntity.notFound().build());
    // }

    //opcao 4 listarCliente por ID tratando retorno de status - simplificando 2
    //200 - quando existe
    //404 - quando nao existe 
    // @GetMapping("/{idCliente}")
    // public ResponseEntity<Cliente> listarCliente(@PathVariable Long idCliente){          // <> = onde fica o type parameter / ResponseEntity pode manipular cabecalho e status
    //     return clienteRepository.findById(idCliente)
    //     .map(ResponseEntity::ok)  //usando method reference
    //     .orElse(ResponseEntity.notFound().build());
    // }



    @GetMapping
    public List<Cliente> Listar() {
        
        // opcao5
        //return clienteRepository.findByNomeContaining("o"); //testetando para trazer todos nomes quem tem a letra "o"

        // opcao4
        // return clienteRepository.findByNome("Carol");
        
        //opcao3
        return clienteRepository.findAll();

        //opcao 2    com sql do jpa
        // return manager.createQuery("from Cliente", Cliente.class).getResultList();

        //opcao 1 fixa
        // Cliente cliente1 = new Cliente();
        // cliente1.setId(1L);
        // cliente1.setNome("Roberto");
        // cliente1.setTelefone("99999999");
        // cliente1.setEmail("roberto@gmail.com");

        // Cliente cliente2 = new Cliente();
        // cliente2.setId(2L);
        // cliente2.setNome("Carol");
        // cliente2.setTelefone("88888888");
        // cliente2.setEmail("carol@gmail.com");

        // return Arrays.asList(cliente1, cliente2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //forma direta de setar o status sem precisar do ResponseEntity
    public Cliente adicionarCliente(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Cliente> atualizarCliente(@Valid @PathVariable Long idCliente, @RequestBody Cliente cliente){
        if(!clienteRepository.existsById(idCliente)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(idCliente); // para atualizar o id senao o metodo save abaixo criaria um novo
        cliente = clienteRepository.save(cliente);      
        return ResponseEntity.ok(cliente);

    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long idCliente){
        if(!clienteRepository.existsById(idCliente)){
            return ResponseEntity.notFound().build(); //404 - nao existe
        }
        clienteRepository.deleteById(idCliente);
        return ResponseEntity.noContent().build(); //204 - executa a operacao e nao tem retorno de body . exemplo para delete
    }

}
