package com.aula.pos.msusuario.service.impl;

import com.aula.pos.msusuario.dto.UsuarioRequest;
import com.aula.pos.msusuario.dto.UsuarioResponse;
import com.aula.pos.msusuario.exception.ConflictException;
import com.aula.pos.msusuario.exception.ResourceNotFoundException;
import com.aula.pos.msusuario.mapper.UsuarioMapper;
import com.aula.pos.msusuario.model.Usuario;
import com.aula.pos.msusuario.repository.UsuarioRepository;
import com.aula.pos.msusuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public UsuarioResponse save(UsuarioRequest request) {
        var usuario = mapper.toEntity(request);
        validarUnicidade(usuario);
        return mapper.toResponse(repository.save(usuario));
    }

    private void validarUnicidade(Usuario usuario) {
        if (repository.existsByEmail(usuario.getEmail())) {
            throw new ConflictException("E-mail já cadastrado");
        }
        if (repository.existsByMatricula(usuario.getMatricula())) {
            throw new ConflictException("Matrícula já cadastrada");
        }
    }
}
