package com.aula.pos.msusuario.service;

import com.aula.pos.msusuario.dto.UsuarioRequest;
import com.aula.pos.msusuario.dto.UsuarioResponse;

import java.util.UUID;

public interface UsuarioService {

    UsuarioResponse findById(UUID id);

    UsuarioResponse save(UsuarioRequest request);
}
