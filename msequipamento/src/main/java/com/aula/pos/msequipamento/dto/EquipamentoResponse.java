package com.aula.pos.msequipamento.dto;

import java.util.UUID;

public record EquipamentoResponse(
        UUID id,
        String nome,
        String tipo,
        String patrimonio,
        Boolean disponivel
) {
}
