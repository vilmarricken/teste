create table estado(
  oid character(32) primary key,
  nome varchar(50),
  sigla varchar(2)
);

create table cidade (
  oid character(32) primary key,
  nome varchar(50),
  ddd varchar(2),
  estado character(32)
);

ALTER TABLE cidade ADD CONSTRAINT fkestado FOREIGN KEY (estado) REFERENCES estado (oid) MATCH FULL;

create table bairro(
  oid character(32) primary key,
  nome varchar(50),
  cidade character(32)
);

ALTER TABLE bairro ADD CONSTRAINT fkcidade FOREIGN KEY (cidade) REFERENCES cidade (oid) MATCH FULL;

CREATE TABLE conta
(
  oid character(32) primary key,
  nome varchar(255),
  email varchar(255),
  telefone varchar(50),
  cpf varchar(20),
  login varchar(50),
  senha varchar(100)
);

create table endereco
(
  oid character(32) primary key,
  localidade varchar(255),
  bairro character(32),
  complemento varchar(500),
  numero varchar(10),
  tipo varchar(32),
  conta character(32)
);

ALTER TABLE endereco ADD CONSTRAINT fkendereco FOREIGN KEY (conta) REFERENCES conta (oid) MATCH FULL;
ALTER TABLE endereco ADD CONSTRAINT fkbairro FOREIGN KEY (bairro) REFERENCES bairro (oid) MATCH FULL;

create table estabelecimento
(
  oid character(32) primary key,
  nome varchar(255),
  razaoSocial varchar(255),
  cnpj varchar(50)
);