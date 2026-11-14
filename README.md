# Sistema de Empréstimo de Equipamentos

## Arquitetura

```
Cliente -> api-gateway -> msusuario
                       -> msequipamento
                       -> msemprestimo -> msusuario     (OpenFeign)
                                       -> msequipamento (OpenFeign)

Todos se registram no discovery-server (Eureka).
```

| Aplicação        | Responsabilidade                              | Banco                |
|------------------|-----------------------------------------------|----------------------|
| discovery-server | Registro e descoberta dos serviços (Eureka)   | -                    |
| api-gateway      | Ponto de entrada e roteamento (`lb://`)       | -                    |
| msusuario        | Cadastrar e consultar usuários                | `ms-usuarios-db`     |
| msequipamento    | Cadastrar, consultar, reservar e liberar      | `ms-equipamentos-db` |
| msemprestimo     | Solicitar empréstimos e registrar devoluções  | `ms-emprestimos-db`  |

## Portas

| Aplicação                              | Porta         |
|----------------------------------------|---------------|
| discovery-server                       | 8761          |
| api-gateway                            | 8080          |
| msusuario, msequipamento, msemprestimo | 0 (aleatória) |

## Como executar

1. Inicie o MySQL na porta 3306 (usuário `root`, sem senha). Os bancos e as tabelas são criados pelo Flyway.
2. Suba, nesta ordem: `discovery-server`, `msusuario`, `msequipamento`, `msemprestimo` e `api-gateway`
   (`./mvnw spring-boot:run` em cada pasta).
3. Importe `emprestimo-equipamentos.postman_collection.json` no Postman.

## Exemplos de requisições

Todas pela Gateway em `http://localhost:8080`.

```http
POST /api/v1/usuarios
{ "nome": "Ana Souza", "email": "ana@escola.edu", "matricula": "2026001" }
```

```http
POST /api/v1/equipamentos
{ "nome": "Notebook Dell", "tipo": "NOTEBOOK", "patrimonio": "PAT-0001" }
```

```http
POST /api/v1/emprestimos
{ "usuarioId": "<id do usuário>", "equipamentoId": "<id do equipamento>" }
```

Resposta `201 Created`:

```json
{ "id": "...", "usuarioId": "...", "equipamentoId": "...", "status": "ATIVO" }
```

```http
PATCH /api/v1/emprestimos/{id}/devolucao
{ "condicao": "AVARIADO" }
```

Resposta `200 OK` com `"status": "DEVOLVIDO"`. Antes de concluir, o msemprestimo altera a condição do
equipamento no msequipamento via OpenFeign, protegido pelo Circuit Breaker `equipamentoService`.
Se o msequipamento estiver indisponível, a resposta é `503` e o empréstimo continua `ATIVO`.

```http
PUT /api/v1/equipamentos/{id}/condicao
{ "condicao": "BOM" }
```

Valores aceitos: `BOM` e `AVARIADO`. Equipamento inexistente retorna `404`.

Estado do Circuit Breaker: `GET /msemprestimo/actuator/circuitbreakers` pela Gateway.
