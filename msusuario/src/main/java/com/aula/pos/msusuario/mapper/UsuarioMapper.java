package com.aula.pos.msusuario.mapper;

import com.aula.pos.msusuario.dto.UsuarioRequest;
import com.aula.pos.msusuario.dto.UsuarioResponse;
import com.aula.pos.msusuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {
        return new Usuario(
                null,
                request.nome().trim(),
                request.email().trim().toLowerCase(),
                request.matricula().trim()
        );
    }

    public UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getMatricula()
        );
    }
}
