package com.aula.pos.msequipamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false, unique = true, length = 50)
    private String patrimonio;

    @Column(nullable = false)
    private Boolean disponivel;

    private UUID emprestimoId;
}
