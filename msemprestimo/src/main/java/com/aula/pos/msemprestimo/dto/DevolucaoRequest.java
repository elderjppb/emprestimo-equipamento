package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.enums.CondicaoEquipamento;
import jakarta.validation.constraints.NotNull;

public record DevolucaoRequest(

        @NotNull(message = "Condição é obrigatória")
        CondicaoEquipamento condicao
) {
}
