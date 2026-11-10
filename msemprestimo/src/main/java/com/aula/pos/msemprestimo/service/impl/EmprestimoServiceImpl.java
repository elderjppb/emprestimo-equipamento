package com.aula.pos.msemprestimo.service.impl;

import com.aula.pos.msemprestimo.client.EquipamentoClient;
import com.aula.pos.msemprestimo.client.UsuarioClient;
import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.exception.ConflictException;
import com.aula.pos.msemprestimo.exception.ResourceNotFoundException;
import com.aula.pos.msemprestimo.mapper.EmprestimoMapper;
import com.aula.pos.msemprestimo.model.Emprestimo;
import com.aula.pos.msemprestimo.repository.EmprestimoRepository;
import com.aula.pos.msemprestimo.service.EmprestimoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository repository;
    private final EmprestimoMapper mapper;
    private final UsuarioClient usuarioClient;
    private final EquipamentoClient equipamentoClient;

    @Override
    public EmprestimoResponse solicitar(EmprestimoRequest request) {
        validarUsuario(request.usuarioId());

        var emprestimo = mapper.toEntity(request);
        reservarEquipamento(emprestimo);

        return mapper.toResponse(salvarOuLiberarReserva(emprestimo));
    }

    @Override
    @Transactional(readOnly = true)
    public EmprestimoResponse findById(UUID id) {
        return mapper.toResponse(findByIdOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmprestimoResponse> findByUsuarioId(UUID usuarioId) {
        return repository.findByUsuarioIdOrderByDataEmprestimoDesc(usuarioId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EmprestimoResponse registrarDevolucao(UUID id) {
        var emprestimo = findByIdOrThrow(id);

        if (repository.registrarDevolucao(id, LocalDateTime.now()) == 0) {
            throw new ConflictException("Empréstimo não está ativo: " + id);
        }

        equipamentoClient.liberar(emprestimo.getEquipamentoId(), id);
        return mapper.toResponse(findByIdOrThrow(id));
    }

    private void validarUsuario(UUID usuarioId) {
        try {
            usuarioClient.findById(usuarioId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(usuarioId);
        }
    }

    private void reservarEquipamento(Emprestimo emprestimo) {
        var equipamentoId = emprestimo.getEquipamentoId();

        try {
            equipamentoClient.findById(equipamentoId);
            equipamentoClient.reservar(equipamentoId, emprestimo.getId());
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(equipamentoId);
        } catch (FeignException.Conflict e) {
            throw new ConflictException("Equipamento indisponível: " + equipamentoId);
        }
    }

    private Emprestimo salvarOuLiberarReserva(Emprestimo emprestimo) {
        try {
            return repository.save(emprestimo);
        } catch (RuntimeException e) {
            equipamentoClient.liberar(emprestimo.getEquipamentoId(), emprestimo.getId());
            throw e;
        }
    }

    private Emprestimo findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
