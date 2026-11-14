package com.aula.pos.msemprestimo.gateway;

import com.aula.pos.msemprestimo.client.EquipamentoClient;
import com.aula.pos.msemprestimo.dto.CondicaoRequest;
import com.aula.pos.msemprestimo.dto.EquipamentoResponse;
import com.aula.pos.msemprestimo.enums.CondicaoEquipamento;
import com.aula.pos.msemprestimo.exception.ExternalServiceException;
import com.aula.pos.msemprestimo.exception.ResourceNotFoundException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EquipamentoGateway {

    private final EquipamentoClient equipamentoClient;

    @CircuitBreaker(name = "equipamentoService", fallbackMethod = "fallback")
    public EquipamentoResponse alterarCondicao(UUID id, CondicaoEquipamento condicao) {
        return equipamentoClient.alterarCondicao(id, new CondicaoRequest(condicao));
    }

    private EquipamentoResponse fallback(UUID id, CondicaoEquipamento condicao, Throwable exception) {
        if (exception instanceof FeignException.NotFound) {
            throw new ResourceNotFoundException(id);
        }

        if (exception instanceof FeignException.FeignClientException clientException) {
            throw new ExternalServiceException(
                    HttpStatus.valueOf(clientException.status()),
                    "Requisição recusada pelo serviço de equipamentos"
            );
        }

        throw new ExternalServiceException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "Serviço de equipamentos temporariamente indisponível"
        );
    }
}
