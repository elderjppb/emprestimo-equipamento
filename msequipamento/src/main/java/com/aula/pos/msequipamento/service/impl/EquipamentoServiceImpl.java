package com.aula.pos.msequipamento.service.impl;

import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.exception.ConflictException;
import com.aula.pos.msequipamento.exception.ResourceNotFoundException;
import com.aula.pos.msequipamento.mapper.EquipamentoMapper;
import com.aula.pos.msequipamento.model.Equipamento;
import com.aula.pos.msequipamento.repository.EquipamentoRepository;
import com.aula.pos.msequipamento.service.EquipamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EquipamentoServiceImpl implements EquipamentoService {

    private final EquipamentoRepository repository;
    private final EquipamentoMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public EquipamentoResponse findById(UUID id) {
        return mapper.toResponse(findByIdOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipamentoResponse> findDisponiveis() {
        return repository.findByDisponivelTrue()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public EquipamentoResponse save(EquipamentoRequest request) {
        var equipamento = mapper.toEntity(request);

        if (repository.existsByPatrimonio(equipamento.getPatrimonio())) {
            throw new ConflictException("Patrimônio já cadastrado");
        }

        return mapper.toResponse(repository.save(equipamento));
    }

    @Override
    @Transactional
    public EquipamentoResponse reservar(UUID id, UUID emprestimoId) {
        var reservado = repository.reservar(id, emprestimoId) > 0;
        var equipamento = findByIdOrThrow(id);

        if (!reservado && !emprestimoId.equals(equipamento.getEmprestimoId())) {
            throw new ConflictException("Equipamento indisponível: " + id);
        }

        return mapper.toResponse(equipamento);
    }

    @Override
    @Transactional
    public void liberar(UUID id, UUID emprestimoId) {
        if (repository.liberar(id, emprestimoId) == 0) {
            findByIdOrThrow(id);
        }
    }

    private Equipamento findByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
