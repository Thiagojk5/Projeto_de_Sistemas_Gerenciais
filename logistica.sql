
CREATE DATABASE logistica;
USE logistica;



CREATE TABLE cliente (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    cpf_cnpj    VARCHAR(14)  NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE funcionario (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nome    VARCHAR(100) NOT NULL,
    cargo   VARCHAR(50)  NOT NULL
) ENGINE=InnoDB;


CREATE TABLE motorista (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    cpf           VARCHAR(11)  NOT NULL UNIQUE,
    numero_cnh    VARCHAR(11)  NOT NULL UNIQUE,
    validade_cnh  varchar(10)         NOT NULL
) ENGINE=InnoDB;


CREATE TABLE categoria_cnh (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    tipo_cnh  VARCHAR(2) NOT NULL UNIQUE   -- A, B, C, D, E, AB, AC...
) ENGINE=InnoDB;

CREATE TABLE veiculo (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    placa               VARCHAR(8)    NOT NULL UNIQUE,
    modelo              VARCHAR(50)   NOT NULL,
    tipo                VARCHAR(30)   NOT NULL,
    capacidade_de_carga DECIMAL(10,2) NOT NULL,
    ano                 int      NOT NULL,
    status              VARCHAR(20)   NOT NULL DEFAULT 'disponivel'
) ENGINE=InnoDB;

CREATE TABLE rota (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    origem            VARCHAR(100)  NOT NULL,
    destino           VARCHAR(100)  NOT NULL,
    distancia_km      DECIMAL(8,2)  NOT NULL,
    tempo_estimado    varchar(45)          NOT NULL
) ENGINE=InnoDB;


CREATE TABLE endereco (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    cep         VARCHAR(8)   NOT NULL,
    logradouro  VARCHAR(100) NOT NULL,
    numero      VARCHAR(10)  NOT NULL,
    bairro      VARCHAR(60)  NOT NULL,
    cidade      VARCHAR(60)  NOT NULL,
    uf          CHAR(2)      NOT NULL,
    id_cliente  INT          NOT NULL,
    CONSTRAINT fk_endereco_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE email_cliente (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nome_email  VARCHAR(100) NOT NULL,
    id_cliente  INT          NOT NULL,
    CONSTRAINT fk_email_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE telefone_cliente (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    ddd         CHAR(2)     NOT NULL,
    numero      VARCHAR(9)  NOT NULL,
    id_cliente  INT         NOT NULL,
    CONSTRAINT fk_telefone_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE telefone_motorista (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    ddd           CHAR(2)    NOT NULL,
    numero        VARCHAR(9) NOT NULL,
    id_motorista  INT        NOT NULL,
    CONSTRAINT fk_telefone_motorista
        FOREIGN KEY (id_motorista) REFERENCES motorista (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE email_motorista (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nome_email    VARCHAR(100) NOT NULL,
    id_motorista  INT          NOT NULL,
    CONSTRAINT fk_email_motorista
        FOREIGN KEY (id_motorista) REFERENCES motorista (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- N:N entre motorista e categoria_cnh
CREATE TABLE motorista_categoria (
    id_motorista  INT NOT NULL,
    id_categoria  INT NOT NULL,
    PRIMARY KEY (id_motorista, id_categoria),
    CONSTRAINT fk_mc_motorista
        FOREIGN KEY (id_motorista) REFERENCES motorista (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_mc_categoria
        FOREIGN KEY (id_categoria) REFERENCES categoria_cnh (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE manutencao (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    data        varchar(10)          NOT NULL,
    tipo        VARCHAR(50)   NOT NULL,
    custo       DECIMAL(10,2) NOT NULL,
    descricao   VARCHAR(255),
    id_veiculo  INT           NOT NULL,
    CONSTRAINT fk_manutencao_veiculo
        FOREIGN KEY (id_veiculo) REFERENCES veiculo (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE entrega (
    id                    INT AUTO_INCREMENT PRIMARY KEY,
    data_solicitacao      varchar(10)          NOT NULL,
    data_prevista         varchar(10)         NOT NULL,
    data_realizacao       varchar(10)          NULL,
    status                VARCHAR(20)   NOT NULL DEFAULT 'pendente',
    valor_do_frete        DECIMAL(10,2) NOT NULL,
    id_cliente            INT           NOT NULL,
    id_endereco           INT           NOT NULL,
    id_rota               INT           NOT NULL,
    id_funcionario        INT           NOT NULL,
    CONSTRAINT fk_entrega_cliente
        FOREIGN KEY (id_cliente) REFERENCES cliente (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_entrega_endereco
        FOREIGN KEY (id_endereco) REFERENCES endereco (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_entrega_rota
        FOREIGN KEY (id_rota) REFERENCES rota (id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_entrega_funcionario
        FOREIGN KEY (id_funcionario) REFERENCES funcionario (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE item (
    id                INT AUTO_INCREMENT PRIMARY KEY,
    descricao         VARCHAR(150)  NOT NULL,
    peso              DECIMAL(10,2) NOT NULL,
    volume            DECIMAL(10,2) NOT NULL,
    valor_declarado   DECIMAL(10,2) NOT NULL,
    id_entrega        INT           NOT NULL,
    CONSTRAINT fk_item_entrega
        FOREIGN KEY (id_entrega) REFERENCES entrega (id)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE viagem (
    id             INT AUTO_INCREMENT PRIMARY KEY,
    data_saida     varchar(10),
    data_chegada   varchar(10),
    id_entrega     INT NOT NULL,
    id_motorista   INT NOT NULL,
    id_veiculo     INT NOT NULL,
    CONSTRAINT fk_viagem_entrega
        FOREIGN KEY (id_entrega) REFERENCES entrega (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_viagem_motorista
        FOREIGN KEY (id_motorista) REFERENCES motorista (id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_viagem_veiculo
        FOREIGN KEY (id_veiculo) REFERENCES veiculo (id)
        ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

use logistica;
select * from cliente;
select * from endereco;
select * from funcionario;