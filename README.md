# Sistema de Manutenção Industrial

## 📋 Descrição

Sistema de gerenciamento de manutenção industrial desenvolvido em Java com interface de linha de comando. O sistema permite o controle completo do processo de manutenção de máquinas industriais, incluindo cadastro de máquinas, técnicos, peças e gerenciamento de ordens de manutenção.

## 🚀 Funcionalidades

### Principais Recursos
- **Cadastro de Máquinas**: Registro de máquinas industriais com nome, setor e status
- **Gestão de Técnicos**: Cadastro de técnicos com especialidades
- **Controle de Peças**: Gerenciamento de estoque de peças e componentes
- **Ordens de Manutenção**: Criação e acompanhamento de ordens de manutenção
- **Associação de Peças**: Vinculação de peças às ordens de manutenção
- **Controle de Status**: Acompanhamento do status das máquinas (OPERACIONAL, EM_MANUTENCAO, etc.)

### Menu Principal
```
 __________________________________
| SISTEMA DE MANUTENÇÃO INDUSTRIAL |
|----------------------------------|
| 1. Cadastrar máquina             |
| 2. Cadastrar técnico             |
| 3. Cadastrar peça                |
| 4. Criar ordem de manutenção     |
| 5. Associar peças à ordem        |
| 6. Executar manutenção           |
|----------------------------------|
| 0. Sair                          |
|__________________________________|
```

## 🛠️ Tecnologias Utilizadas

- **Java 22**: Linguagem de programação principal
- **Maven**: Gerenciamento de dependências
- **MySQL 8.0**: Banco de dados
- **JDBC**: Conexão com banco de dados
- **MySQL Connector/J 8.0.33**: Driver para MySQL

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/org/example/
│   │   ├── DAO/                    # Camada de Acesso a Dados
│   │   │   ├── AssociarPecaDAO.java
│   │   │   ├── MaquinaDAO.java
│   │   │   ├── OrdemManutencaoDAO.java
│   │   │   ├── PecaDAO.java
│   │   │   └── TecnicoDAO.java
│   │   ├── Model/                  # Modelos de Dados
│   │   │   ├── Maquina.java
│   │   │   ├── OrdemManutencao.java
│   │   │   ├── OrdemManutencaoPeca.java
│   │   │   ├── OrdemPeca.java
│   │   │   ├── peca.java
│   │   │   └── Tecnico.java
│   │   ├── Util/                   # Utilitários
│   │   │   └── Conexao.java
│   │   └── Main.java               # Classe principal
│   └── resources/
└── test/
    └── java/
```

## 🗄️ Modelo de Dados

### Entidades Principais

#### Máquina
- `id`: Identificador único
- `nome`: Nome da máquina
- `setor`: Setor onde a máquina está localizada
- `status`: Status atual (OPERACIONAL, EM_MANUTENCAO, etc.)

#### Técnico
- `id`: Identificador único
- `nome`: Nome do técnico
- `especialidade`: Especialidade do técnico

#### Peça
- `id`: Identificador único
- `nome`: Nome da peça
- `estoque`: Quantidade em estoque

#### Ordem de Manutenção
- `id`: Identificador único
- `maquina`: Referência à máquina
- `tecnico`: Referência ao técnico responsável
- `dataSolicitada`: Data da solicitação
- `status`: Status da ordem (PENDENTE, EXECUTADA, etc.)

## ⚙️ Configuração e Instalação

### Pré-requisitos
- Java 22 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior

### Configuração do Banco de Dados

1. **Criar o banco de dados:**
```sql
CREATE DATABASE ManutencaoIndustrial;
```

2. **Configurar a conexão:**
Edite o arquivo `src/main/java/org/example/Util/Conexao.java` e ajuste as configurações:
```java
private static String URL = "jdbc:mysql://localhost:3306/ManutencaoIndustrial?useSSL=false&serverTimezone=UTC";
private static String USER = "root";
private static String PASSWORD = "sua_senha_mysql";
```

### Executando o Projeto

1. **Clone o repositório:**
```bash
git clone <url-do-repositorio>
cd ManutencaoIndustrial
```

2. **Compile o projeto:**
```bash
mvn compile
```

3. **Execute o sistema:**
```bash
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 📊 Funcionalidades Detalhadas

### 1. Cadastro de Máquinas
- Validação de duplicação por nome e setor
- Status inicial: OPERACIONAL
- Controle de campos obrigatórios

### 2. Cadastro de Técnicos
- Validação de duplicação por nome
- Registro de especialidade
- Controle de campos obrigatórios

### 3. Cadastro de Peças
- Controle de estoque inicial
- Validação de duplicação por nome
- Verificação de estoque não negativo

### 4. Criação de Ordens de Manutenção
- Seleção de máquinas operacionais
- Atribuição de técnico responsável
- Atualização automática do status da máquina para EM_MANUTENCAO
- Uso de transações para garantir consistência

### 5. Associação de Peças
- Listagem de ordens pendentes
- Seleção de peças disponíveis
- Verificação de estoque suficiente
- Registro da quantidade necessária

## 🔧 Arquitetura

O projeto segue o padrão DAO (Data Access Object) com as seguintes camadas:

- **Model**: Entidades de domínio
- **DAO**: Camada de acesso a dados
- **Util**: Utilitários e configurações
- **Main**: Interface de usuário e lógica de negócio

## 🚨 Tratamento de Erros

- Validação de campos obrigatórios
- Verificação de duplicação de registros
- Controle de estoque insuficiente
- Transações para garantir consistência dos dados
- Tratamento de exceções SQL

## 📝 Notas de Desenvolvimento

- O sistema utiliza transações para operações críticas
- Implementa validações de negócio antes das operações
- Interface de linha de comando para simplicidade
- Estrutura preparada para futuras expansões

## 🤝 Contribuição

Para contribuir com o projeto:

1. Faça um fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

Desenvolvido por Murilo Kerschbaum - Sistema de Manutenção Industrial

---

**Versão**: 1.0-SNAPSHOT  
**Última atualização**: 2024
