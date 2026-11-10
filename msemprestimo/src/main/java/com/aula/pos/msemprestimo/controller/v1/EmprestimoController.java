package com.aula.pos.msemprestimo.controller.v1;

import com.aula.pos.msemprestimo.dto.EmprestimoRequest;
import com.aula.pos.msemprestimo.dto.EmprestimoResponse;
import com.aula.pos.msemprestimo.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmprestimoResponse solicitar(@Valid @RequestBody EmprestimoRequest request) {
        return emprestimoService.solicitar(request);
    }

    @GetMapping("/{id}")
    public EmprestimoResponse findById(@PathVariable UUID id) {
        return emprestimoService.findById(id);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public List<EmprestimoResponse> findByUsuarioId(@PathVariable UUID usuarioId) {
        return emprestimoService.findByUsuarioId(usuarioId);
    }

    @PatchMapping("/{id}/devolucao")
    public EmprestimoResponse registrarDevolucao(@PathVariable UUID id) {
        return emprestimoService.registrarDevolucao(id);
    }
}
