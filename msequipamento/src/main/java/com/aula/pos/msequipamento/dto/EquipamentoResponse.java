package com.aula.pos.msequipamento.dto;

import com.aula.pos.msequipamento.enums.CondicaoEquipamento;

import java.util.UUID;

public record EquipamentoResponse(
        UUID id,
        String nome,
        String tipo,
        String patrimonio,
        Boolean disponivel,
        CondicaoEquipamento condicao
) {
}
