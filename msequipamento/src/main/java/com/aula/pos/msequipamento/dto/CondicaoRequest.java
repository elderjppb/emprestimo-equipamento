package com.aula.pos.msequipamento.dto;

import com.aula.pos.msequipamento.enums.CondicaoEquipamento;
import jakarta.validation.constraints.NotNull;

public record CondicaoRequest(

        @NotNull(message = "Condição é obrigatória")
        CondicaoEquipamento condicao
) {
}
