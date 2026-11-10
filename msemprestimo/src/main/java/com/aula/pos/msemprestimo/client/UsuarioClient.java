package com.aula.pos.msemprestimo.client;

import com.aula.pos.msemprestimo.dto.UsuarioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "msusuario")
public interface UsuarioClient {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioResponse findById(@PathVariable UUID id);
}
