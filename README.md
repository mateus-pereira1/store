# Baozi Store API

API REST (Java + Spring Boot + Spring Data JPA + H2) para controle de clientes, produtos e pedidos da Baozi Store.

Aluno: Mateus Pereira Sousa - RU 5155577

## Tecnologias
Java 25, Spring Boot 4.2.0, Spring Data JPA, Bean Validation, banco H2 (em memória), Maven e Postman.

## Como executar
1. Abra a pasta do projeto no IntelliJ IDEA.
2. Aguarde o Maven baixar as dependências.
3. Execute a classe `StoreApplication`.
4. A API fica disponível em http://localhost:8080

O banco H2 é em memória: os dados são apagados quando a aplicação é encerrada.

## Estrutura
- `model`: entidades Cliente, Produto e Pedido
- `repository`: interfaces JpaRepository
- `service`: regras de negócio
- `controller`: endpoints REST
- `exception`: tratamento de erros em JSON

## Endpoints
Os mesmos para `/clientes`, `/produtos` e `/pedidos`:

| Método | Rota | Descrição | Status |
|---|---|---|---|
| POST | /recurso | Criar | 201 |
| GET | /recurso | Listar todos | 200 |
| GET | /recurso/{id} | Consultar por ID | 200 ou 404 |
| PUT | /recurso/{id} | Atualizar (opcional) | 200 |
| DELETE | /recurso/{id} | Apagar | 204 |

## Exemplos de corpo (JSON)
Cliente (POST /clientes):

    { "nome": "Mateus5155577", "clienteDesde": "2026-11-25" }

Produto (POST /produtos):

    { "nome": "Baozi de carne", "preco": 8.50, "estoque": true }

Pedido (POST /pedidos):

    { "clienteId": 1, "produtoId": 1, "quantidade": 3 }