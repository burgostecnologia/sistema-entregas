package com.burgostecnologia.entregaapi.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.burgostecnologia.entregaapi.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
//import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="entrega")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private BigDecimal taxa;

    @JsonProperty(access = Access.READ_ONLY) // usamos a entity para receber o requestbody, logo teoricamente poderia serenviado campos que maliciosamente nao deveriam ser atualizados por isso conseguimos travar com essa notacao
    private LocalDateTime dataPedido;

    @JsonProperty(access = Access.READ_ONLY) // usamos a entity para receber o requestbody, logo teoricamente poderia serenviado campos que maliciosamente nao deveriam ser atualizados por isso conseguimos travar com essa notacao
    private LocalDateTime dataFinalizacao;

    @ManyToOne //faz com que o modelo de banco crie o join (cria a fk)
    //@JoinColumn(name = "nome da cluna que quer fazer o join") //Pode escolher com qual campo fazer o join , se não usar o padrao fica o tabela_id 
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded //não cria uma tabela é mais para fazer abstracao(melhor uso de OO) mas grava mesmo esses campos na tabela entrega
    private Destinatario destinatario;

    //@Enumerated(EnumType.ORDINAL) // dessa forma pegaria o indice do enum ex. posição 0,1,2...
    @Enumerated(EnumType.STRING) // dessa forma pegaria texto
    @JsonProperty(access = Access.READ_ONLY) // usamos a entity para receber o requestbody, logo teoricamente poderia serenviado campos que maliciosamente nao deveriam ser atualizados por isso conseguimos travar com essa notacao
    private StatusEntrega statusEntrega;
}
