package com.burgostecnologia.entregaapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.burgostecnologia.entregaapi.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>{

    // Criando metodo customizado com  o suporte do JPA - Query Method
    // Seguir padrao usando por exemplo um prefixo valido = find +  By + campo obs:mesmo o campo nome da entity Cliente estar minusculo o campo inicia com letra maiuscula
    List<Cliente> findByNome(String nome);    
    
    // + Containing é tipo o like
    List<Cliente> findByNomeContaining(String nome);    

    Optional<Cliente> findByEmail(String email);
}
