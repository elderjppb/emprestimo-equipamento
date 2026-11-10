package com.aula.pos.msemprestimo.mapper;

import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.enums.StatusEmprestimo;
import com.aula.pos.msemprestimo.model.Emprestimo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class EmprestimoMapper {

    public Emprestimo toEntity(EmprestimoRequest request) {
        return new Emprestimo(
                UUID.randomUUID(),
                request.usuarioId(),
                request.equipamentoId(),
                LocalDateTime.now(),
                null,
                StatusEmprestimo.ATIVO
        );
    }

    public EmprestimoResponse toResponse(Emprestimo emprestimo) {
        return new EmprestimoResponse(
                emprestimo.getId(),
                emprestimo.getUsuarioId(),
                emprestimo.getEquipamentoId(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getStatus()
        );
    }
}
