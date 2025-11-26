# 🏨 Sistema de Hotel — Gestão Completa com Java, Spring Boot e JDBC

Este projeto consiste no desenvolvimento de um Sistema de Gestão Hoteleira, criado como trabalho acadêmico e aprimorado para uso em portfólio.
A aplicação integra backend em Java/Spring Boot, banco PostgreSQL via JDBC e frontend interativo com HTML, CSS e JavaScript.

O objetivo foi construir uma aplicação completa e bem estruturada, aplicando boas práticas, arquitetura limpa e um CRUD totalmente funcional para hóspedes e funcionários.

## **🚀 Tecnologias Utilizadas**

**Backend:**

- Java 17

- Spring Boot 3.5

- Spring Web (API REST)

- Jakarta Validation

- JDBC

- PostgreSQL

- Maven

 **Frontend:**

- HTML5

- CSS3

- JavaScript (Fetch API)

- Interface responsiva e moderna

- Estruturas atualizadas e organizadas

## **🎯 Objetivo do Sistema**

O sistema tem como meta gerenciar informações de hóspedes e funcionários através de operações completas:

- Criar

- Listar

- Atualizar

- Excluir

Também demonstra domínio de:

- Arquitetura MVC

- Arquitetura em camadas (Controller → Service → Repository → DB)

- Boas práticas de programação

- Integração front-end + back-end

- Uso de DTOs

- Aplicação de JDBC com SQL puro

## **🧱 Arquitetura do Projeto**

O projeto foi construído seguindo duas abordagens combinadas:

✅ MVC

Model — Entidades (Pessoa, Hóspede, Funcionário)

View — Frontend com HTML, CSS e JS

Controller — Endpoints REST

✅ Arquitetura em Camadas
Controller → Service → Repository → Database


Controller: recebe requisições e retorna as respostas REST

Service: contém as regras de negócio e validações

Repository: executa SQL usando JDBC

Model: representa as entidades da aplicação

Essa organização garante desacoplamento, clareza e escalabilidade.

## 🧩 Modelagem do Sistema
Classes principais
Pessoa (abstrata)

cpf

nome

idade

Hospede (extends Pessoa)

rg

fidelidade

Funcionario (extends Pessoa)

funcao

Essas estruturas representam o domínio básico de um sistema hoteleiro.

## 📦 DTO – Data Transfer Objects

Foram implementados DTOs para evitar exposição direta das entidades internas:

PessoaDTO

HospedeDTO

FuncionarioDTO

Benefícios:

Mais segurança

Padronização dos dados recebidos

Facilidade de validação

Código mais limpo e organizado

A conversão DTO ↔ Entity é feita na camada Service.

## 🌐 Controllers (API REST)

Os controllers expõem métodos CRUD completos:

Métodos disponíveis

POST — cadastrar

GET — consultar ou listar

PUT — atualizar

DELETE — excluir

Com:

Respostas padronizadas (ApiResponse)

Tratamento de erros

Retorno em JSON

## 🧠 Services – Regras de Negócio

A camada Service realiza:

Validações (ex.: não cadastrar menor de 16 anos como funcionário)

Conversão DTO → Model

Prevenção de CPFs duplicados

Atualização apenas dos campos enviados

Verificação de existência antes de alterações ou remoções

Tratamento de exceções

🗄 Repository – JDBC + PostgreSQL

O acesso ao banco é feito via SQL puro, usando:

PreparedStatement

Connection

DriverManager

Essa abordagem foi mantida para reforçar o domínio da base da comunicação com bancos de dados e evitar abstrações excessivas.

## 🛢 Banco de Dados
Tabelas
hospede

cpf (PK)

nome

idade

rg

fidelidade

funcionario

cpf (PK)

nome

idade

funcao

Banco utilizado: PostgreSQL

## 🎨 Front-End

O frontend foi reformulado para entregar uma interface:

limpa

intuitiva

moderna

responsiva

Funcionalidades:

Cadastro

Listagem

Exclusão

Atualização (PUT)

Toggle moderno para fidelidade

Código HTML, CSS e JS organizados

A comunicação com o backend é feita pela Fetch API:

fetch(url, {
  method,
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(data)
});

🔗 Endpoints da API
Hóspedes
Método	Rota	Descrição
GET	/api/hospedes	Lista todos
GET	/api/hospedes/{cpf}	Consulta por CPF
POST	/api/hospedes	Cadastrar
PUT	/api/hospedes/{cpf}	Atualizar
DELETE	/api/hospedes/{cpf}	Excluir
Funcionários
Método	Rota	Descrição
GET	/api/funcionarios	Lista todos
GET	/api/funcionarios/{cpf}	Consulta por CPF
POST	/api/funcionarios	Cadastrar
PUT	/api/funcionarios/{cpf}	Atualizar
DELETE	/api/funcionarios/{cpf}	Excluir
🧭 Diagrama de Arquitetura
     FRONT-END (HTML / CSS / JS)
                 |
                 v
           CONTROLLERS
                 |
                 v
              SERVICE
      Validações / DTO / Regras
                 |
                 v
            REPOSITORY
      JDBC + SQL + PostgreSQL
                 |
                 v
             DATABASE

## 🔁 Fluxo de Funcionamento

Usuário executa ação no frontend

JS envia requisição via Fetch

Controller recebe

Service valida

Repository manipula o banco

Service retorna resposta

Controller devolve JSON

Frontend atualiza interface

🛠 Como Executar
Backend
mvn spring-boot:run

Frontend

Acessar:

http://localhost:8080/index.html

Banco de Dados

Criar as tabelas no PostgreSQL e ajustar as credenciais no application.properties.

## ✅ Conclusão

Este projeto demonstra habilidades em:

- Java + Spring Boot

- API REST

- JDBC e SQL

- Arquitetura MVC

- Arquitetura em camadas

- Padrões de projeto

- Integração completa front-end + back-end

- Criação de interfaces modernas e funcionais

O sistema está completo, funcional e estruturado com boas práticas, representando bem a evolução técnica e profissional do desenvolvimento.
