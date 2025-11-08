# 🏍️ SysTrack

Sistema completo de gerenciamento de pátios de veículos desenvolvido com Java Spring Boot, oferecendo controle eficiente de motocicletas com filtros avançados, paginação e autenticação segura.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias Utilizadas](#️-tecnologias-utilizadas)
- [Funcionalidades](#-funcionalidades)
- [Pré-requisitos](#-pré-requisitos)
- [Como Executar](#-como-executar)
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

### Gerenciamento de Motocicletas
- ✔️ Cadastro completo de motocicletas
- ✔️ Rastreamento de quilometragem
- ✔️ Controle de status (Funcional/Manutenção)
- ✔️ Associação com pátios
- ✔️ Associação com usuários
- ✔️ Filtros por modelo, ano, quilometragem e status

### Gerenciamento de Usuários
- ✔️ Sistema de autenticação
- ✔️ Controle de acesso por perfis
- ✔️ Cadastro e gerenciamento de usuários

### Recursos Técnicos
- 🔎 Filtros dinâmicos com `JpaSpecificationExecutor`
- 📄 Paginação e ordenação em todos os endpoints
- 📖 Validações detalhadas com mensagens amigáveis
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

- **Aplicação Web:** http://localhost:8080/home
- **Console H2:** http://localhost:8080/h2-console

#### Credenciais do H2 Console
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** *(deixe em branco)*

---

## 🗃️ Modelo de Dados

### Moto

```java
{
  "id": Long,
  "placa": String,
  "placa": String,
  "modelo": String,
  "ano": int,
  "quilometragem": Double,
  "status": Status,       // FUNCIONAL ou MANUTENCAO
  "patio": Patio            // Pátio associado
  "usuario": Usuario            // Usuário associado
}
```

### Patio

```java
{
  "id": Long,
  "nome": String,
  "endereco": String,
}
```

### Usuario

```java
{
  "id": Long,
  "nome": String,
  "email": String,        // E-mail
  "senha": String,     // Senha (criptografada)
  "role": String          // ADMIN ou USER
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
