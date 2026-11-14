package com.aula.pos.msemprestimo.service;

import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.enums.CondicaoEquipamento;

import java.util.List;
import java.util.UUID;

public interface EmprestimoService {

    EmprestimoResponse solicitar(EmprestimoRequest request);

    EmprestimoResponse findById(UUID id);

    List<EmprestimoResponse> findByUsuarioId(UUID usuarioId);

    EmprestimoResponse registrarDevolucao(UUID id, CondicaoEquipamento condicao);
}
