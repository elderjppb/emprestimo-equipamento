package com.aula.pos.msequipamento.service;

import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.enums.CondicaoEquipamento;

import java.util.List;
import java.util.UUID;

public interface EquipamentoService {

    EquipamentoResponse findById(UUID id);

    List<EquipamentoResponse> findDisponiveis();

    EquipamentoResponse save(EquipamentoRequest request);

    EquipamentoResponse reservar(UUID id, UUID emprestimoId);

    void liberar(UUID id, UUID emprestimoId);

    EquipamentoResponse alterarCondicao(UUID id, CondicaoEquipamento condicao);
}
