package com.aula.pos.msemprestimo.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EmprestimoRequest(

        @NotNull(message = "Usuário é obrigatório")
        UUID usuarioId,

        @NotNull(message = "Equipamento é obrigatório")
        UUID equipamentoId
) {
}
