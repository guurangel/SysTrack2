# 🏍️ SysTrack

Sistema completo de gerenciamento de pátios de veículos desenvolvido com Java Spring Boot, oferecendo controle eficiente de motocicletas com filtros avançados, paginação e autenticação segura.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
- [Endpoints da API](#-endpoints-da-api)
- [Modelo de Dados](#️-modelo-de-dados)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Equipe](#-equipe)

---

## 🧾 Sobre o Projeto

**SysTrack** é uma aplicação web moderna para gestão de pátios de veículos, focada no gerenciamento de motocicletas. O sistema oferece uma API RESTful completa com autenticação, validações robustas e filtros dinâmicos para facilitar a administração de frotas.

### Destaques

- ✨ Interface de administração intuitiva
- 🔐 Sistema de autenticação e autorização com Spring Security
- 🔍 Filtros dinâmicos e buscas avançadas
- 📊 Dashboard com estatísticas e métricas
- 📱 API RESTful documentada com Swagger
- 🗃️ Persistência de dados com H2 Database

---

## ⚙️ Tecnologias Utilizadas

### Backend
- **Java 17** - Linguagem de programação
- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Camada de persistência
- **Spring Security** - Autenticação e autorização
- **Hibernate** - ORM (Object-Relational Mapping)

### Banco de Dados
- **H2 Database** - Banco de dados em memória para desenvolvimento

### Ferramentas e Bibliotecas
- **Lombok** - Redução de código boilerplate
- **Jakarta Validation** - Validação de dados
- **Swagger/OpenAPI** - Documentação interativa da API
- **Flyway** - Migração e versionamento de banco de dados
- **Maven** - Gerenciamento de dependências
- **Thymeleaf** - Template engine para páginas web

---

## ✅ Funcionalidades

### Gerenciamento de Pátios
- ✔️ Cadastro, edição e exclusão de pátios
- ✔️ Controle de capacidade máxima
- ✔️ Listagem com filtros e ordenação

### Gerenciamento de Motocicletas
- ✔️ Cadastro completo de motocicletas
- ✔️ Rastreamento de quilometragem
- ✔️ Controle de status (Funcional/Manutenção)
- ✔️ Associação com pátios
- ✔️ Filtros por marca, modelo, ano e status

### Gerenciamento de Usuários
- ✔️ Sistema de autenticação
- ✔️ Controle de acesso por perfis
- ✔️ Cadastro e gerenciamento de usuários

### Recursos Técnicos
- 🔎 Filtros dinâmicos com `JpaSpecificationExecutor`
- 📄 Paginação e ordenação em todos os endpoints
- 📖 Validações detalhadas com mensagens amigáveis
- 📊 Documentação interativa via Swagger UI
- 🧱 Arquitetura em camadas (Controller, Service, Repository)

---

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- ☕ [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- 📦 [Maven 3.8+](https://maven.apache.org/download.cgi)
- 💻 IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

---

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/guurangel/SysTrack2.git
cd SysTrack2
```

### 2. Compile o projeto

```bash
mvn clean install
```

### 3. Execute a aplicação

```bash
mvn spring-boot:run
```

### 4. Acesse a aplicação

A aplicação estará disponível em:

- **Aplicação Web:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **Console H2:** http://localhost:8080/h2-console

#### Credenciais do H2 Console
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(deixe em branco)*

---

## 📌 Endpoints da API

### Pátios (Yards)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/yards` | Lista todos os pátios (com paginação) |
| GET | `/api/yards/{id}` | Busca pátio por ID |
| POST | `/api/yards` | Cria novo pátio |
| PUT | `/api/yards/{id}` | Atualiza pátio existente |
| DELETE | `/api/yards/{id}` | Remove pátio |

### Motocicletas (Motorcycles)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/motorcycles` | Lista todas as motocicletas (com filtros) |
| GET | `/api/motorcycles/{id}` | Busca motocicleta por ID |
| POST | `/api/motorcycles` | Cadastra nova motocicleta |
| PUT | `/api/motorcycles/{id}` | Atualiza motocicleta existente |
| DELETE | `/api/motorcycles/{id}` | Remove motocicleta |

### Usuários (Users)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/users` | Lista todos os usuários |
| GET | `/api/users/{id}` | Busca usuário por ID |
| POST | `/api/users` | Cria novo usuário |
| PUT | `/api/users/{id}` | Atualiza usuário |
| DELETE | `/api/users/{id}` | Remove usuário |

#### Parâmetros de Filtro (Query Params)

**Motocicletas:**
- `brand` - Filtrar por marca
- `model` - Filtrar por modelo
- `modelYear` - Filtrar por ano
- `status` - Filtrar por status (FUNCIONAL, MANUTENCAO)
- `page` - Número da página (padrão: 0)
- `size` - Tamanho da página (padrão: 10)
- `sort` - Campo de ordenação (ex: `plate,asc`)

---

## 🗃️ Modelo de Dados

### Motocicleta (Motorcycle)

```java
{
  "id": Long,
  "plate": String,        // Placa do veículo
  "brand": String,        // Marca
  "model": String,        // Modelo
  "modelYear": Integer,   // Ano do modelo
  "status": String,       // FUNCIONAL ou MANUTENCAO
  "km": Double,           // Quilometragem
  "yard": Yard            // Pátio associado
}
```

### Pátio (Yard)

```java
{
  "id": Long,
  "name": String,         // Nome do pátio
  "address": String,      // Endereço
  "maxCapacity": Integer  // Capacidade máxima
}
```

### Usuário (User)

```java
{
  "id": Long,
  "username": String,     // Nome de usuário
  "email": String,        // E-mail
  "password": String,     // Senha (criptografada)
  "role": String          // Perfil de acesso
}
```

---

## 🗂 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com/systrack/
│   │       ├── config/         # Configurações (Security, Swagger)
│   │       ├── controller/     # Controllers REST
│   │       ├── service/        # Lógica de negócio
│   │       ├── repository/     # Repositories JPA
│   │       ├── domain/         # Entidades JPA
│   │       ├── dto/            # Data Transfer Objects
│   │       ├── specification/  # Specifications para filtros
│   │       └── exception/      # Tratamento de exceções
│   └── resources/
│       ├── static/             # Arquivos estáticos (CSS, JS)
│       ├── templates/          # Templates Thymeleaf
│       ├── db/migration/       # Scripts Flyway
│       └── application.yml     # Configurações da aplicação
└── test/                       # Testes unitários e integração
```

### Arquitetura em Camadas

O projeto segue o padrão MVC com separação clara de responsabilidades:

1. **Controller** - Recebe requisições HTTP e retorna respostas
2. **Service** - Implementa regras de negócio
3. **Repository** - Acessa e persiste dados
4. **Domain** - Define as entidades do domínio
5. **DTO** - Transfere dados entre camadas

---

## 👨‍💻 Equipe

### Gustavo Rangel
💼 Estudante de Análise e Desenvolvimento de Sistemas - FIAP  
🔗 [LinkedIn](https://www.linkedin.com/in/gustavoorangel)

### David Rapeckman
💼 Estudante de Análise e Desenvolvimento de Sistemas - FIAP  
🔗 [LinkedIn](https://www.linkedin.com/in/davidrapeckman)

### Luis Felippe Morais
💼 Estudante de Análise e Desenvolvimento de Sistemas - FIAP  
🔗 [LinkedIn](https://www.linkedin.com/in/luis-felippe-morais-das-neves-16219b2b9)

---

## 📝 Licença

Este projeto foi desenvolvido como atividade acadêmica na FIAP.

---
