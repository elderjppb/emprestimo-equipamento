package com.aula.pos.msequipamento.controller.v1;

import com.aula.pos.msequipamento.dto.EquipamentoRequest;
import com.aula.pos.msequipamento.dto.EquipamentoResponse;
import com.aula.pos.msequipamento.service.EquipamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoResponse save(@Valid @RequestBody EquipamentoRequest request) {
        return equipamentoService.save(request);
    }

    @GetMapping("/{id}")
    public EquipamentoResponse findById(@PathVariable UUID id) {
        return equipamentoService.findById(id);
    }

    @GetMapping("/disponiveis")
    public List<EquipamentoResponse> findDisponiveis() {
        return equipamentoService.findDisponiveis();
    }

    @PutMapping("/{id}/reservas/{emprestimoId}")
    public EquipamentoResponse reservar(@PathVariable UUID id, @PathVariable UUID emprestimoId) {
        return equipamentoService.reservar(id, emprestimoId);
    }

    @DeleteMapping("/{id}/reservas/{emprestimoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void liberar(@PathVariable UUID id, @PathVariable UUID emprestimoId) {
        equipamentoService.liberar(id, emprestimoId);
    }
}
