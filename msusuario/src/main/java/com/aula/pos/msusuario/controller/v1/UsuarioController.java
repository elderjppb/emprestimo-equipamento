package com.aula.pos.msusuario.controller.v1;

import com.aula.pos.msusuario.dto.UsuarioRequest;
import com.aula.pos.msusuario.dto.UsuarioResponse;
import com.aula.pos.msusuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse save(@Valid @RequestBody UsuarioRequest request) {
        return usuarioService.save(request);
    }

    @GetMapping("/{id}")
    public UsuarioResponse findById(@PathVariable UUID id) {
        return usuarioService.findById(id);
    }
}
