package com.aula.pos.msusuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150, message = "Nome deve possuir no máximo 150 caracteres")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 150, message = "E-mail deve possuir no máximo 150 caracteres")
        String email,

        @NotBlank(message = "Matrícula é obrigatória")
        @Size(max = 20, message = "Matrícula deve possuir no máximo 20 caracteres")
        String matricula
) {
}
