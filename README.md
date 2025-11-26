Sistema de Hotel — Gestão Completa com Java, Spring Boot e PostgreSQL

Backend com Java + Spring Boot

Banco PostgreSQL acessado via JDBC

Frontend em HTML, CSS e JavaScript

O sistema permite gerenciar hóspedes e funcionários com operações completas e arquitetura organizada.

Tecnologias Utilizadas
Backend

Java 17

Spring Boot 3

Spring Web (API REST)

JDBC

PostgreSQL

Maven

Frontend

HTML5

CSS3

JavaScript (Fetch API)

Interface responsiva

Objetivo do Sistema

O sistema realiza operações de CRUD para:

Hóspedes

Funcionários

Operações disponíveis:

Criar

Listar

Consultar

Atualizar

Excluir

O projeto também demonstra:

Arquitetura MVC

Arquitetura em camadas

Boas práticas de programação

Comunicação REST

SQL via JDBC

Integração entre front-end e back-end

Arquitetura do Projeto

O sistema utiliza dois padrões complementares:

MVC

Model — entidades do domínio

View — interface em HTML, CSS e JS

Controller — endpoints REST

Arquitetura em camadas
Controller → Service → Repository → Banco de Dados

Camadas

Controller: recebe requisições e retorna JSON

Service: contém regras de negócio e validações

Repository: executa SQL usando JDBC

Model: representa as classes principais do sistema

Essa estrutura mantém o projeto organizado, escalável e fácil de manter.

Modelagem do Sistema
Classe abstrata Pessoa

cpf

nome

idade

Classe Hospede (extends Pessoa)

rg

fidelidade

Classe Funcionario (extends Pessoa)

funcao

Essa modelagem representa o domínio básico de um hotel real.

API REST (Controllers)
Endpoints de Hóspedes
Método	Rota	Descrição
GET	/api/hospedes	Listar todos
GET	/api/hospedes/{cpf}	Consultar por CPF
POST	/api/hospedes	Cadastrar
PUT	/api/hospedes/{cpf}	Atualizar
DELETE	/api/hospedes/{cpf}	Excluir
Endpoints de Funcionários
Método	Rota	Descrição
GET	/api/funcionarios	Listar todos
GET	/api/funcionarios/{cpf}	Consultar por CPF
POST	/api/funcionarios	Cadastrar
PUT	/api/funcionarios/{cpf}	Atualizar
DELETE	/api/funcionarios/{cpf}	Excluir
Camada Service

A camada Service é responsável por:

Validar dados

Verificar duplicidade (ex.: CPF já existente)

Confirmar existência antes de atualizar ou excluir

Tratar exceções vindas do JDBC

Aplicar regras de negócio

Repository — JDBC + PostgreSQL

O banco de dados é acessado por SQL puro utilizando:

DriverManager

PreparedStatement

ResultSet

Essa abordagem reforça o entendimento completo sobre a comunicação direta com o banco.

Banco de Dados
Tabela hospede

cpf (PK)

nome

idade

rg

fidelidade

Tabela funcionario

cpf (PK)

nome

idade

funcao

Banco utilizado: PostgreSQL.

Front-End

O frontend foi desenvolvido com HTML, CSS e JavaScript, apresentando uma interface limpa, moderna e responsiva.

Funcionalidades implementadas:

Cadastro

Listagem dinâmica

Atualização

Exclusão

Alternância de fidelidade com toggle estilizado

Todas as requisições utilizam Fetch API:

fetch(url, {
  method,
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(data)
});

Diagrama da Arquitetura
 FRONT-END (HTML / CSS / JS)
            |
            v
        CONTROLLER
            |
            v
        SERVICE
 Regras de Negócio / Validações
            |
            v
       REPOSITORY
  JDBC + SQL + PostgreSQL
            |
            v
         DATABASE

Fluxo de Funcionamento

O usuário executa uma ação no frontend

O JavaScript envia a requisição para o backend

O Controller recebe e direciona para a camada Service

O Service processa a lógica

O Repository executa SQL no banco

O Service retorna a resposta

O Controller envia a resposta JSON

A interface atualiza automaticamente

Como Executar
Backend
mvn spring-boot:run

Frontend

Acesse no navegador:

http://localhost:8080/index.html

Banco de Dados

Crie as tabelas no PostgreSQL e ajuste as credenciais em:

src/main/resources/application.properties
