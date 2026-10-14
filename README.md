🚀 API REST — Gerenciamento de Dados Espaciais (`DadoColetado`)

API REST desenvolvida em **Java 25** e **Spring Boot 3** para o gerenciamento e registro de dados de medições espaciais coletados por sondas (ex: Temperatura, Pressão, Radiação, Composição Atmosférica e Velocidade do Vento).

A aplicação conta com arquitetura em camadas, validações com Bean Validation, persistência de dados em dois ambientes (**H2** em teste e **MySQL** em desenvolvimento via Flyway Migrations), documentação interativa com **Swagger / OpenAPI** e containerização com **Dockerfile Multi-Stage** e **Docker Compose**.

---

🛠️ Tecnologias Utilizadas

* Java 25
* Spring Boot 3 (Spring Data JPA, Spring Web, Spring Validation)
* Lombok
* Flyway Migration (Gerenciamento e versionamento do schema de banco)
* H2 Database (Banco em memória para o profile `test`)
* MySQL 8 (Banco relacional para o profile `dev`)
* Docker & Docker Compose (Containerização com Multi-Stage Build)
* SpringDoc OpenAPI / Swagger UI (Documentação interativa da API)

---

📐 Estrutura do Modelo de Dados

#1. Entidade `DadoColetado`
`dadoId`: `Long` (Chave primária, autoincremento)
`sondaNome`: `String` (Obrigatório, min 3 e max 100 caracteres)
`agenciaEspacial`: `String` (Obrigatório, min 3 e max 100 caracteres)
`dataColeta`: `LocalDate` (Obrigatório, data menor ou igual à data atual)
`tipoDado`: `TipoDado` (Enum)
`valorMedido`: `BigDecimal` (Obrigatório)
`unidadeMedida`: `String` (Obrigatório, min 3 e max 100 caracteres)

#2. Enum `TipoDado`
Valores possíveis:
`TEMPERATURA`
`PRESSAO`
`RADIACAO`
`COMPOSICAO_ATMOSFERICA`
`VELOCIDADE_VENTO`

---

⚙️ Configuração de Profiles e Banco de Dados

A aplicação está configurada com dois perfis de execução:

| Profile | Banco de Dados | DDL / Migrations | Finalidade |
| :--- | :--- | :--- | :--- |
| **`test`** | H2 (Em memória) | `import.sql` (Carga inicial) | Desenvolvimento rápido e testes locais |
| **`dev`** | MySQL 8 (Docker) | Flyway (`V1` e `V2`) | Ambiente de desenvolvimento integrado com MySQL |

---

🚀 Como Executar a Aplicação

#Pré-requisitos
* Java 25 (ou superior) instalado
* Maven instalado (ou uso do wrapper `./mvnw`)
* Docker & Docker Compose instalados (para execução com MySQL/Docker)

---

#Option 1: Executando no Profile `test` (H2 Database)

Por padrão, o arquivo `application.properties` está configurado para o profile `test`.

1. Compile e rode o projeto via Maven:
   ```bash
   ./mvnw spring-boot:run
   A aplicação iniciará na porta 8080.

2. Acesso ao H2 Console:
   * URL: http://localhost:8080/h2-console
   * JDBC URL: jdbc:h2:mem:db_dados_coletados
   * User: sa
   * Password: (em branco)

#Option 2: Executando no Profile dev (MySQL + Docker Compose)

1. Suba o container do MySQL 8 via Docker Compose:
   docker compose up -d

2. Execute a aplicação ativando o profile dev:
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

3. O Flyway executará automaticamente as migrações:
   * V1__create_table_tb_dado_coletado.sql (Criação da tabela)
   * V2__insert_tb_dado_coletado.sql (Carga inicial de dados)

#Option 3: Construindo e Executando a Imagem Docker (Multi-Stage Build)

1. Construir a imagem Docker:
   - docker build -t registro-sondas-api .

2. Executar a aplicação via container:
   - docker run -p 8080:8080 registro-sondas-api

---

📑 Documentação da API (Swagger / OpenAPI)
Com a aplicação em execução (em qualquer profile), acesse a documentação interativa para testar as requisições HTTP:

Swagger UI: http://localhost:8080/swagger-ui/index.html

OpenAPI Docs (JSON): http://localhost:8080/v3/api-docs

---

# * Exemplo de Corpo da Requisição (POST /dados-coletados)
{
  "sondaNome": "Perseverance",
  "agenciaEspacial": "NASA",
  "dataColeta": "2026-10-14",
  "tipoDado": "PRESSAO",
  "valorMedido": 610.50,
  "unidadeMedida": "Pascal"
}
