# Tech Challenge Sistema de Gerenciamento de Pedidos (Logistica de Entrega)

## Visão Geral

Descrição funcional: Este serviço cuidará de toda a logística relacionada à entrega de pedidos, desde a atribuição de entregadores até o rastreamento das entregas em tempo real. A função inclui calcular as rotas mais eficientes, estimar tempos de entrega e fornecer atualizações de status aos clientes.

Tecnologias e abordagens:
 - Java 21
 - Spring Boot
 - Spring Data JPA
 - Arquitetura Hexagonal
 - Banco de Dados Relacional PostgreSQL

## Requisitos

Antes de iniciar, certifique-se de ter os seguintes requisitos atendidos:

- **Java 21**: O projeto utiliza o Java 21. Certifique-se de que sua IDE está configurada com o Java 21.
- **Maven**: Usado para gerenciar dependências e construir o projeto.

## Passos para Configuração

1. **Clone o Repositório:**

   Abra o terminal e clone o repositório usando o comando:

   ```bash
   git clone https://github.com/bpcavalcante/fiap-logistica-entrega

2. **Acesse a Branch main:**

   Depois de clonar o repositório, navegue até o diretório do projeto e altere para a branch main:

   ```bash
   git checkout main

3. **Importe o Projeto na IDE:**

- Abra sua IDE preferida (por exemplo, IntelliJ IDEA ou Eclipse).
- Certifique-se de que o **Java 21** está configurado como JDK.
- Importe o projeto como um projeto Maven existente. 

4. **Construir o Projeto:**

   No terminal, dentro do diretório do projeto, execute o comando Maven para construir o projeto, ou em algumas IDE já constroem automaticamente:

   ```bash
   mvn clean install

5. **Executar o Projeto:**
   Depois de construir o projeto, você pode executá-lo diretamente na IDE ou via linha de comando:
   ```bash
   mvn spring-boot:run

  O servidor será iniciado na porta **8083**


6. **Subir o container Docker:**
   Você precisará subir o container com as configurações, que estão no arquivo docker-compose dentro do projeto:
   ```bash
   docker-compose up

  O servidor PostgreSQL será iniciado na porta **5453**
  

7. **Testando o Sistema:**
   Use os comandos curl abaixo para testar as funcionalidades do sistema:
   - **Cadastrar Entrega**
     ```bash
      curl --location 'http://localhost:8083/entrega' \
      --header 'Content-Type: application/json' \
      --data '{
          "idPedido": 12345,
          "longitude": -38.54321,
          "latitude": -3.76543,
          "cep": "60000000"
      }'
   - **Atualizar Entrega**
     ```bash
      curl --location --request PATCH 'http://localhost:8083/entrega/2' \
      --header 'Content-Type: application/json' \
      --data '{
          "idPedido": 12345,
          "longitude": -38.54321,
          "latitude": -3.76543,
          "cep": "23000000"
      }'
   - **Buscar por CEP**
     ```bash
      curl --location 'http://localhost:8083/entrega/cep/60000000' \
      --header 'Content-Type: application/json'
   - **Buscar por ID**
     ```bash
      curl --location 'http://localhost:8083/entrega/1' \
      --header 'Content-Type: application/json'
   - **Buscar todas entregas**
     ```bash
      curl --location 'http://localhost:8083/entrega' \
      --header 'Content-Type: application/json'
8. **Verificando doc Swagger:**
   Acesse o link http://localhost:8083/swagger-ui/index.html com o projeto rodando
