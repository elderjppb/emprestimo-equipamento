package com.aula.pos.msemprestimo.client;

import com.aula.pos.msemprestimo.dto.CondicaoRequest;
import com.aula.pos.msemprestimo.dto.EquipamentoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "msequipamento")
public interface EquipamentoClient {

    @GetMapping("/api/v1/equipamentos/{id}")
    EquipamentoResponse findById(@PathVariable UUID id);

    @PutMapping("/api/v1/equipamentos/{id}/reservas/{emprestimoId}")
    EquipamentoResponse reservar(@PathVariable UUID id, @PathVariable UUID emprestimoId);

    @DeleteMapping("/api/v1/equipamentos/{id}/reservas/{emprestimoId}")
    void liberar(@PathVariable UUID id, @PathVariable UUID emprestimoId);

    @PutMapping("/api/v1/equipamentos/{id}/condicao")
    EquipamentoResponse alterarCondicao(@PathVariable UUID id, @RequestBody CondicaoRequest request);
}
