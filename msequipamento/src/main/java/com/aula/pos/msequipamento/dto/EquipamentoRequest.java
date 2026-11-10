package com.aula.pos.msequipamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EquipamentoRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve possuir no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "Tipo é obrigatório")
        @Size(max = 50, message = "Tipo deve possuir no máximo 50 caracteres")
        String tipo,

        @NotBlank(message = "Patrimônio é obrigatório")
        @Size(max = 50, message = "Patrimônio deve possuir no máximo 50 caracteres")
        String patrimonio
) {
}
