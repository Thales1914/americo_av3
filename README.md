# Sistema de Hotel — Gestão Completa com Java, Spring Boot e JDBC

Este projeto consiste no desenvolvimento de um Sistema de Gestão Hoteleira.  
A aplicação integra backend em Java/Spring Boot, banco PostgreSQL via JDBC e frontend interativo com HTML, CSS e JavaScript.

O objetivo é oferecer uma aplicação completa e bem estruturada, aplicando boas práticas, arquitetura limpa e um CRUD funcional para hóspedes e funcionários.

---

## Tecnologias Utilizadas

### Backend
- Java 17  
- Spring Boot 3.5  
- Spring Web (API REST)  
- Jakarta Validation  
- JDBC  
- PostgreSQL  
- Maven  

### Frontend
- HTML5  
- CSS3  
- JavaScript (Fetch API)  
- Interface responsiva e organizada  

---

## Objetivo do Sistema

O sistema gerencia informações de hóspedes e funcionários por meio de operações:

- Criar  
- Listar  
- Atualizar  
- Excluir  

Também demonstra:

- Arquitetura MVC  
- Arquitetura em camadas (Controller → Service → Repository → DB)  
- Boas práticas de programação  
- Integração entre front-end e back-end  
- Aplicação de JDBC com SQL puro  

---

## Arquitetura do Projeto

O projeto segue duas abordagens principais:

### MVC
- **Model:** Pessoa, Hospede, Funcionario  
- **View:** HTML, CSS e JS  
- **Controller:** Endpoints REST  

### Arquitetura em Camadas
Controller → Service → Repository → Database

yaml
Copiar código

- **Controller:** Recebe requisições e retorna respostas REST  
- **Service:** Contém regras de negócio  
- **Repository:** Executa SQL com JDBC  
- **Model:** Representa as entidades  

Essa estrutura garante desacoplamento e facilita a manutenção.

---

## Modelagem do Sistema

**Classes principais**

### Pessoa (abstrata)
- cpf  
- nome  
- idade  

### Hospede (extends Pessoa)
- rg  
- fidelidade  

### Funcionario (extends Pessoa)
- funcao  

Essas classes representam o domínio do sistema.

---

## DTO – Data Transfer Objects

DTOs foram utilizados para padronizar dados de entrada:

- PessoaDTO  
- HospedeDTO  
- FuncionarioDTO  

Vantagens:
- Dados mais seguros  
- Validação facilitada  
- Código mais limpo  

A conversão entre DTO e Entity ocorre na camada Service.

---

## Controllers (API REST)

Os controllers expõem CRUD completo com:

- POST → cadastrar  
- GET → listar ou consultar  
- PUT → atualizar  
- DELETE → excluir  

Inclui:
- Respostas padronizadas (ApiResponse)  
- Tratamento de erros  
- Retorno em JSON  

---

## Services – Regras de Negócio

A camada Service realiza:

- Validações  
- Conversão DTO → Model  
- Prevenção de CPFs duplicados  
- Atualização somente dos campos enviados  
- Verificação antes de alterar ou excluir  
- Tratamento de exceções do JDBC  

---

## Repository – JDBC + PostgreSQL

O Repository usa SQL puro com:

- PreparedStatement  
- Connection  
- DriverManager  

A escolha do JDBC possibilita maior controle sobre as operações no banco.

---

## Banco de Dados

### Tabelas

#### hospede
- cpf (PK)  
- nome  
- idade  
- rg  
- fidelidade  

#### funcionario
- cpf (PK)  
- nome  
- idade  
- funcao  

Banco utilizado: **PostgreSQL**

---

## Front-End

O frontend foi desenvolvido para ser:

- Limpo  
- Intuitivo  
- Responsivo  

Funcionalidades:
- Cadastro  
- Listagem  
- Exclusão  
- Atualização  

Exemplo da comunicação com o backend:

```javascript
fetch(url, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(data)
});
Endpoints da API
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

Diagrama de Arquitetura
yaml
Copiar código
FRONT-END (HTML / CSS / JS)
        |
        v
   CONTROLLERS
        |
        v
     SERVICE
 Validações / Regras
        |
        v
   REPOSITORY
  JDBC + SQL + PostgreSQL
        |
        v
    DATABASE
Fluxo de Funcionamento
O usuário executa uma ação no frontend

O JS envia a requisição via Fetch

O Controller recebe

O Service valida e processa

O Repository interage com o banco

O Service retorna a resposta

O Controller devolve JSON

O frontend atualiza a interface

Como Executar
Backend
arduino
Copiar código
mvn spring-boot:run
Frontend
Acesse:

bash
Copiar código
http://localhost:8080/index.html
