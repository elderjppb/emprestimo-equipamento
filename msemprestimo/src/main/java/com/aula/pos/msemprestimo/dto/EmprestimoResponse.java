package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.enums.StatusEmprestimo;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmprestimoResponse(
        UUID id,
        UUID usuarioId,
        UUID equipamentoId,
        LocalDateTime dataEmprestimo,
        LocalDateTime dataDevolucao,
        StatusEmprestimo status
) {
}
