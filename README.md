# Projeto_de_Sistemas_Gerenciais
Trabalho da matéria de Projeto de sistemas gerenciais(Logistica)

# Sistema de Logística

Sistema desktop em Java para gerenciar a operação de uma transportadora: clientes, motoristas, frota, rotas, entregas, itens transportados e viagens. Os dados ficam em um banco MySQL e a interface é feita com Java Swing.

## Integrantes

- Thiago Isac Aguiar Silva
- Gabriel Miserani
- João Pedro Arantes

## Tecnologias

- Java (JDK 11 ou superior)
- Java Swing (interface gráfica, criada no NetBeans)
- JDBC com MySQL (driver MySQL Connector/J)
- MySQL 8 (banco `logistica`)
- NetBeans IDE

## Arquitetura

O projeto segue o padrão em camadas **Model / DAO / Controller**, com as telas por cima:

| Camada | Responsabilidade |
|---|---|
| `logistica.model` | Classes de domínio (`ClienteModel`, `EntregaModel`, `ViagemModel` etc.), apenas atributos, construtores, getters e setters. |
| `logistica.dao` | Acesso ao banco com `PreparedStatement`: `inserir`, `atualizar`, `remover`, `buscar` e `exibirTodos`. A classe `Conexao` abre a conexão. |
| `logistica.controller` | Validação dos dados e regras de negócio. Cada método devolve uma mensagem de texto com o resultado da operação. |
| Telas (Swing) | Formulários de cadastro com tabela, que chamam os controllers. |

Fluxo de uma operação: **tela → controller → DAO → banco de dados**.

## Banco de dados

Banco `logistica`, com 16 tabelas (todas InnoDB):

| Grupo | Tabelas |
|---|---|
| Clientes | `cliente`, `endereco`, `telefone_cliente`, `email_cliente` |
| Pessoal | `funcionario`, `motorista`, `telefone_motorista`, `email_motorista` |
| Habilitação | `categoria_cnh`, `motorista_categoria` (relação N:N entre motorista e categoria) |
| Frota | `veiculo`, `manutencao` |
| Operação | `rota`, `entrega`, `item`, `viagem` |

Principais relacionamentos:

- Um **cliente** tem vários endereços, telefones e e-mails.
- Um **motorista** tem vários telefones e e-mails e pode ter várias categorias de CNH.
- Um **veículo** tem várias manutenções.
- Uma **entrega** pertence a um cliente, usa um endereço, uma rota e um funcionário, e possui vários itens.
- Uma **viagem** liga uma entrega a um motorista e a um veículo.

## Funcionalidades

Cadastro completo (salvar, editar, excluir, pesquisar e listar) de:

- Cliente, endereço, telefone e e-mail do cliente
- Funcionário
- Motorista, telefone e e-mail do motorista, categoria de CNH e categoria do motorista
- Veículo e manutenção
- Rota
- Entrega e item
- Viagem

## Como executar

### Pré-requisitos

- JDK 11 ou superior
- MySQL em execução na porta 3306
- NetBeans (ou outra IDE Java)
- MySQL Connector/J adicionado às bibliotecas do projeto

### Passo a passo

1. Execute o script SQL do projeto no MySQL. Ele cria o banco `logistica` e as 16 tabelas (`CREATE DATABASE logistica; USE logistica; ...`).
2. Abra o projeto no NetBeans.
3. Adicione o driver **MySQL Connector/J** em *Bibliotecas* (*Libraries*) do projeto.
4. Confira as credenciais de acesso na classe `logistica.dao.Conexao`:

   ```java
   private final String URL = "jdbc:mysql://localhost:3306/logistica";
   private final String USER = "root";
   private final String SENHA = "sua_senha";
   ```

   Ajuste usuário e senha se o seu MySQL for diferente.
5. Execute a tela desejada (cada tela possui o seu próprio método `main`).

## Observações

- Na ordem de cadastro, registre primeiro as tabelas das quais as outras dependem: cliente, endereço, funcionário, rota, motorista, veículo e categoria de CNH, e só depois entrega, item e viagem.
- Cada tela mostra a mensagem retornada pelo controller (sucesso, dado inválido, registro não encontrado etc.).
