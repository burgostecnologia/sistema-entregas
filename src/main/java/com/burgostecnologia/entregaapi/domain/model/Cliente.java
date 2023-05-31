package com.burgostecnologia.entregaapi.domain.model;

import com.burgostecnologia.entregaapi.domain.ValidationGroups;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cliente")
public class Cliente {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nome")
    @NotBlank //validation de null e branco
    @Size(max=256) //tamanho maximo
    private String nome;
    
    @NotBlank //validation de null e branco
    @Email // formato email
    @Column(name="email")
    private String email;

    @NotBlank //validation de null e branco
    @Size(max=9)
    @Column(name="telefone")
    private String telefone;

    

}
