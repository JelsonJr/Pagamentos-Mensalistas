# Trabalho para disciplina de desenvolvimento web (Pagamentos mensalistas)

## _Desenvolvido por: Jelson Rodrigues_ 👨🏻‍💻

## Tecnologias Utilizadas:

-   Java 21 <img src="https://skillicons.dev/icons?i=java" alt="Icon da linguagem Java" style="height: 24px; width: 24px; vertical-align: middle;" />
-   Apache Maven <img src="https://skillicons.dev/icons?i=maven" alt="Icon do Apache Maven" style="height: 24px; width: 24px; vertical-align: middle;" />
-   Spring Boot <img src="https://skillicons.dev/icons?i=spring" alt="Icon do Spring Boot" style="height: 24px; width: 24px; vertical-align: middle;" />
-   PostgreSQL <img src="https://skillicons.dev/icons?i=postgres" alt="Icon do PostgreSQL" style="height: 24px; width: 24px; vertical-align: middle;" />
-   Postman <img src="https://skillicons.dev/icons?i=postman" alt="Icon do Postman" style="height: 24px; width: 24px; vertical-align: middle;" />

## Funcionalidades:

-   **Cadastro de jogadores e pagamentos**: Adiciona novas entidades ao sistema (Jogador | Pagamento).
-   **Buscar entidade por código (id)**: Realiza busca de uma entidade especifica dentro do sistema através do seu identificador único.
-   **Listar todos os registros de determinada entidade**: Retorna uma lista de todos os registros cadastrados de uma determinada entidade no sistema.
-   **Atualizar registro**: Atualiza as informações de uma entidade existente.
-   **Excluir registro**: Remove uma entidade permanentemente do sistema.

## Endpoints 📋

### Rotas de Jogadores

-   **Endpoint** `/jogadores/`

    -   **GET** `/`:

        -   Descrição: Retorna uma lista de todos os jogadores.
        -   Requisição completa: `GET http://localhost:8080/jogadores/`
        -   Respostas:
            -   `200 OK`: Sucesso, retorna a lista de jogadores (mesmo vazia).
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **GET** `/{codJogador}`:

        -   Descrição: Retorna os dados de um jogador específico baseado no seu código.
        -   Parâmetro: `codJogador` (long)
        -   Requisição completa: `GET http://localhost:8080/jogadores/{codJogador}`
        -   Respostas:
            -   `200 OK`: Jogador encontrado.
            -   `404 NOT FOUND`: Jogador não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **GET** `/{codJogador}/pagamentos`:

        -   Descrição: Retorna os dados de todos os pagamentos feitos por um jogador com base no seu código.
        -   Parâmetro: `codJogador` (long)
        -   Requisição completa: `GET http://localhost:8080/jogadores/{codJogador}/pagamentos`
        -   Respostas:
            -   `200 OK`: Jogador e lista de pagamentos (mesmo que vazia) são retornados com sucesso.
            -   `404 NOT FOUND`: Jogador não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **POST** `/`:

        -   Descrição: Cria um novo jogador.
        -   Corpo da requisição (JSON):
            ```json
            {
            	"nome": "Jelson Rodrigues",
            	"email": "jelson@email.com",
            	"datanasc": "2004-07-27"
            }
            ```
        -   Requisição completa: `POST http://localhost:8080/jogadores/`
        -   Respostas:
            -   `201 CREATED`: Jogador criado com sucesso.
            -   `400 BAD_REQUEST`: Erro na requisição.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **PUT** `/{codJogador}`:

        -   Descrição: Atualiza os dados de um jogador existente.
        -   Parâmetro: `codJogador` (long)
        -   Corpo da requisição (JSON):
            ```json
            {
            	"nome": "Jelson Antonio Rodrigues Junior",
            	"email": "jelson@uepg.com",
            	"datanasc": "2004-07-27"
            }
            ```
        -   Requisição completa: `PUT http://localhost:8080/jogadores/{codJogador}`
        -   Respostas:
            -   `200 OK`: Jogador atualizado com sucesso.
            -   `404 NOT FOUND`: Jogador não encontrado.
            -   `400 BAD_REQUEST`: Erro na requisição.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **DELETE** `/{codJogador}`:
        -   Descrição: Deleta um jogador com base no seu ID.
        -   Parâmetro: `codJogador` (long)
        -   Requisição completa: `DELETE http://localhost:8080/jogadores/{codJogador}`
        -   Respostas:
            -   `204 NO CONTENT`: Jogador deletado com sucesso.
            -   `404 NOT FOUND`: Jogador não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

### Rotas de Pagamentos

-   **Endpoint** `/pagamentos/`

    -   **GET** `/`

        -   Descrição: Retorna a lista de todos os pagamentos registrados no sistema.
        -   Requisição completa: `GET http://localhost:8080/pagamentos/`
        -   Respostas:
            -   `200 OK`: Lista de pagamentos retornada com sucesso (mesmo vazia).
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **GET** `/{codPagamento}`

        -   Descrição: Retorna os detalhes de um pagamento específico com base no seu código.
        -   Parâmetro: `codPagamento` (long)
        -   Requisição completa: `GET http://localhost:8080/pagamentos/{codPagamento}`
        -   Respostas:
            -   `200 OK`: Pagamento encontrado.
            -   `404 NOT FOUND`: Pagamento não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **POST** `/`

        -   Descrição: Cria um novo pagamento para um jogador (ano e mês são opcionais).
        -   Corpo da requisição (JSON):

                {
                  "ano": 2024, // Opcional (default = ano atual)
                  "mes": "OUTUBRO", // Opcional (default = mês atual)
                  "valor": "10.0", // Valor deve ser uma String
                  "jogador": 1 // Código do jogador
                }

    -   Requisição completa: `POST http://localhost:8080/pagamentos/`
    -   Respostas:

        -   `201 CREATED`: Pagamento criado com sucesso.
        -   `400 BAD REQUEST`: Dados do jogador inválidos ou incompletos.
        -   `404 NOT FOUND`: Jogador não encontrado.
        -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **PUT** `/{codPagamento}`

        -   Descrição: Atualiza os detalhes de um pagamento existente (todos os campos passam a ser obrigatórios).
        -   Parâmetro: `codPagamento` (long)
        -   Corpo da requisição (JSON):

                {
                  "ano": 2036,
                  "mes": "DEZEMBRO",
                  "valor": "80.0", // Valor deve ser uma String
                  "jogador": 1 // Código do jogador
                }

        -   Requisição completa: `PUT http://localhost:8080/pagamentos/{codPagamento}`
        -   Respostas:
            -   `200 OK`: Pagamento atualizado com sucesso.
            -   `404 NOT FOUND`: Pagamento ou jogador não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **DELETE** `/`

        -   Descrição: Deleta todos os pagamentos registrados no sistema.
        -   Requisição completa: `DELETE http://localhost:8080/pagamentos/`
        -   Respostas:
            -   `204 NO CONTENT`: Todos os pagamentos deletados com sucesso.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.

    -   **DELETE** `/{codPagamento}`

        -   Descrição: Deleta um pagamento específico com base no código.
        -   Parâmetro: `codPagamento` (long)
        -   Requisição completa: `DELETE http://localhost:8080/pagamentos/{codPagamento}`
        -   Respostas:
            -   `204 NO CONTENT`: Pagamento deletado com sucesso.
            -   `404 NOT FOUND`: Pagamento não encontrado.
            -   `500 INTERNAL SERVER ERROR`: Erro interno do servidor.
