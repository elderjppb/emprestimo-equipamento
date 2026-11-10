package com.aula.pos.msemprestimo.dto;

import java.util.UUID;

public record EquipamentoResponse(
        UUID id,
        String nome,
        String tipo,
        String patrimonio,
        Boolean disponivel
) {
}
