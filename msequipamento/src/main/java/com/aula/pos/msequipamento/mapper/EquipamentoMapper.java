package com.aula.pos.msequipamento.mapper;

import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.enums.CondicaoEquipamento;
import com.aula.pos.msequipamento.model.Equipamento;
import org.springframework.stereotype.Component;

@Component
public class EquipamentoMapper {

    public Equipamento toEntity(EquipamentoRequest request) {
        return new Equipamento(
                null,
                request.nome().trim(),
                request.tipo().trim(),
                request.patrimonio().trim(),
                true,
                CondicaoEquipamento.BOM,
                null
        );
    }

    public EquipamentoResponse toResponse(Equipamento equipamento) {
        return new EquipamentoResponse(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getTipo(),
                equipamento.getPatrimonio(),
                equipamento.getDisponivel(),
                equipamento.getCondicao()
        );
    }
}
