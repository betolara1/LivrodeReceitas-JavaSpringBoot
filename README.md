# 📖 Livro de Receitas

Sistema de gerenciamento de receitas desenvolvido com Spring Boot, permitindo que usuários registrem, visualizem e gerenciem suas receitas favoritas.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.0** (com spring-boot-starter-webmvc)
- **Spring Security** - Autenticação e autorização
- **JWT (JSON Web Token)** - Dependências incluídas (implementação em desenvolvimento)
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **Thymeleaf** - Engine de templates para views
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências

## 📋 Funcionalidades

### Autenticação
- ✅ Registro de novos usuários (`/auth/register`)
- ✅ Login de usuários (`/auth/login`)
- ✅ Autenticação HTTP Basic com Spring Security
- ✅ Codificação de senhas com BCrypt
- ✅ Proteção de rotas com Spring Security
- ⏳ Autenticação JWT (dependências incluídas, implementação em desenvolvimento)

### Receitas
- ✅ Listar todas as receitas (`GET /api/receitas`)
- ✅ Buscar receita por ID (`GET /api/receitas/{id}`)
- ✅ Criar nova receita (`POST /api/receitas`)
- ✅ Deletar receita (`DELETE /api/receitas/{id}`)

### Estrutura de Dados
- **Usuários**: id, username (único), password (codificado com BCrypt)
- **Receitas**: id, título, descrição, tempo de preparo, dificuldade, temperatura, ingredientes (array de objetos), instruções (array de strings), usuário (relacionamento ManyToOne)
- **Categorias**: modelo criado (id, nome, slug, isDefault, userID) - endpoints ainda não implementados
- **Favoritos**: modelo criado (id, userID) - endpoints ainda não implementados

## 🛠️ Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+**
- **PostgreSQL** (para produção) ou **H2** (para desenvolvimento)
- **IDE** de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

## 📦 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd Livro-de-Receitas
```

### 2. Configure o banco de dados

Edite o arquivo `src/main/resources/application.properties` com suas credenciais do PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/livro-receita
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
```

### 3. Crie o banco de dados

No PostgreSQL, execute:

```sql
CREATE DATABASE livro_receita;
```

### 4. Compile o projeto

```bash
mvn clean install
```

### 5. Execute a aplicação

```bash
mvn spring-boot:run
```

Ou execute a classe `LivroDeReceitasApplication.java` diretamente na sua IDE.

A aplicação estará disponível em: `http://localhost:8080`

### 6. Interface Frontend

O projeto inclui uma interface HTML simples localizada em `src/main/resources/static/index.html` que permite testar as funcionalidades da API diretamente no navegador.

## 📡 Endpoints da API

### Autenticação

#### Registrar Usuário
```http
POST /auth/register
Content-Type: application/json

{
  "username": "usuario123",
  "password": "senha123"
}
```

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "usuario123",
  "password": "senha123"
}
```

**Resposta (sucesso):**
```json
{
  "id": 1,
  "username": "usuario123",
  "password": "$2a$10$..."
}
```

**Resposta (erro):**
```json
"Credenciais invalidas"
```

### Receitas

#### Listar Todas as Receitas
```http
GET /api/receitas
Authorization: Basic {credenciais_base64}
```

**Resposta:**
```json
[
  {
    "id": 1,
    "title": "Bolo de Chocolate",
    "description": "Delicioso bolo de chocolate caseiro",
    "ingredients": [
      {
        "item": "Farinha de trigo",
        "quantity": "2 xícaras"
      }
    ],
    "instructions": [
      "Misture os ingredientes secos",
      "Asse por 40 minutos"
    ],
    "usuario": "usuario123"
  }
]
```

#### Buscar Receita por ID
```http
GET /api/receitas/{id}
Authorization: Basic {credenciais_base64}
```

**Resposta:**
```json
{
  "id": 1,
  "title": "Bolo de Chocolate",
  "description": "Delicioso bolo de chocolate caseiro",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Asse por 40 minutos"
  ],
  "usuario": "usuario123"
}
```

#### Criar Receita
```http
POST /api/receitas
Authorization: Basic {credenciais_base64}
Content-Type: application/json

{
  "title": "Bolo de Chocolate",
  "description": "Delicioso bolo de chocolate caseiro",
  "prepTime": "60 minutos",
  "difficulty": "Médio",
  "temperature": "180°C",
  "ingredients": [
    {
      "item": "Farinha de trigo",
      "quantity": "2 xícaras"
    },
    {
      "item": "Açúcar",
      "quantity": "1 xícara"
    }
  ],
  "instructions": [
    "Misture os ingredientes secos",
    "Adicione os ingredientes líquidos",
    "Asse por 40 minutos"
  ]
}
```

**Nota**: O DTO retornado não inclui `prepTime`, `difficulty` e `temperature` (filtrados propositalmente para demonstrar o uso de DTOs).

#### Deletar Receita
```http
DELETE /api/receitas/{id}
Authorization: Basic {credenciais_base64}
```

**Resposta:** `204 No Content`

## 🔒 Segurança

- As rotas de receitas (`/api/receitas/**`) são protegidas e requerem autenticação HTTP Basic
- A autenticação HTTP Basic deve ser enviada no header `Authorization: Basic {credenciais_base64}`
- As rotas de autenticação (`/auth/**`) são públicas
- As senhas são codificadas usando BCrypt
- **Nota**: As dependências JWT estão incluídas no projeto, mas a implementação completa ainda está em desenvolvimento

## 📁 Estrutura do Projeto

```
src/main/java/com/roberto/Livro_de_Receitas/
├── controller/          # Controladores REST
│   ├── AuthController.java
│   └── ReceitasController.java
├── model/              # Entidades JPA
│   ├── UsuariosDB.java
│   ├── ReceitasDB.java
│   ├── CategoriasDB.java
│   └── FavoritosDB.java
├── repository/         # Repositórios JPA
│   ├── UsuariosRepository.java
│   └── ReceitasRepository.java
├── service/            # Lógica de negócio
│   ├── UsuariosService.java
│   ├── ReceitasService.java
│   └── UsuariosDetailsService.java
├── DTO/               # Data Transfer Objects
│   ├── UsuariosDTO.java
│   ├── ReceitasDTO.java
│   └── StandardErrorDTO.java
├── security/          # Configurações de segurança
│   ├── SecurityConfig.java
├── exception/         # Tratamento de exceções
│   ├── GlobalExceptionHandler.java
│   └── RecursoNaoEncontradoException.java
└── LivroDeReceitasApplication.java
```

## 🧪 Testes

Para executar os testes:

```bash
mvn test
```

## 🔧 Configurações Adicionais

### Hibernate

O projeto está configurado para criar/atualizar as tabelas automaticamente:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Console H2 (Desenvolvimento)

Se estiver usando H2, o console estará disponível em:
`http://localhost:8080/h2-console`

## 📝 Notas de Desenvolvimento

- O projeto utiliza **DTOs** para transferência de dados, separando a camada de apresentação da camada de persistência
  - O `ReceitasDTO` filtra propositalmente alguns campos (prepTime, difficulty, temperature) para demonstrar o uso de DTOs
- **Lombok** é utilizado para reduzir código boilerplate (getters, setters, construtores)
- O tratamento de exceções é feito globalmente através do `GlobalExceptionHandler`
- A autenticação atual utiliza **HTTP Basic Auth** com BCrypt para codificação de senhas
- As dependências JWT estão incluídas no projeto, mas a implementação completa ainda está em desenvolvimento
- As receitas possuem relacionamento ManyToOne com usuários, permitindo rastrear o criador de cada receita
- Os modelos `CategoriasDB` e `FavoritosDB` estão criados, mas os endpoints ainda não foram implementados

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT.

## 👤 Autor

**Roberto**

---

Desenvolvido usando Spring Boot
