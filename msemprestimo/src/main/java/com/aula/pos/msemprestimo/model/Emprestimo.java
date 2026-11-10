package com.aula.pos.msemprestimo.model;

import com.aula.pos.msemprestimo.enums.StatusEmprestimo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_emprestimos")
public class Emprestimo {

    @Id
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "usuario_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID usuarioId;

    @Column(name = "equipamento_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
    private UUID equipamentoId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataEmprestimo;

    private LocalDateTime dataDevolucao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusEmprestimo status;
}
