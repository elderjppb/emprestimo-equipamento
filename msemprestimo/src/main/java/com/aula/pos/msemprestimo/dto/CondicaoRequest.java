package com.aula.pos.msemprestimo.dto;

import com.aula.pos.msemprestimo.enums.CondicaoEquipamento;

public record CondicaoRequest(
        CondicaoEquipamento condicao
) {
}
