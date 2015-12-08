create table estado(
  oid character(32) primary key,
  nome varchar(50),
  sigla varchar(2)
);

create table cidade(
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

CREATE TABLE conta(
  oid character(32) primary key,
  nome varchar(255),
  email varchar(255),
  telefone varchar(50),
  cpf varchar(20),
  login varchar(50),
  senha varchar(100)
);

create table endereco(
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

create table estabelecimento(
  oid character(32) primary key,
  nome varchar(255),
  razaoSocial varchar(255),
  cnpj varchar(50)
);
 
create table cardapio(
  oid character(32) primary key,
  ativo int,
  corFundo varchar(8),
  titulo varchar(50)
);
  
create table bloco(
  oid character(32) primary key,
  titulo varchar(50),
  descricao varchar(200),
  imagemTitulo bytea,
  background varchar(8),
  ordem int,
  cardapio character(32),
  blocoSuperior character(32)
);

ALTER TABLE bloco ADD CONSTRAINT fkcardapio FOREIGN KEY (cardapio) REFERENCES cardapio (oid) MATCH FULL;

ALTER TABLE bloco ADD CONSTRAINT fkblocosuperior FOREIGN KEY (blocoSuperior) REFERENCES bloco (oid) MATCH FULL;

create table produto(
  oid character(32) primary key,
  codigo int,
  nome varchar(100),
  descricao varchar(500),
  imagem bytea,
  ativo int,
  tipoProduto int
);

create table produtoBloco(
  bloco character(32),
  produto character(32),
  ordem int,
  primary key (bloco, produto)
);

ALTER TABLE produtoBloco ADD CONSTRAINT fkprodutoBlocoBloco FOREIGN KEY (bloco) REFERENCES bloco (oid) MATCH FULL;

ALTER TABLE produtoBloco ADD CONSTRAINT fkprodutoBlocoProduto FOREIGN KEY (produto) REFERENCES produto (oid) MATCH FULL;

create table listaPreco(
  oid character(32) primary key,
  ativo int
);

create table produtoPreco(
  produto character(32),
  listaPreco character(32),
  preco numeric(10,2),
  primary key (produto, listaPreco)
);

ALTER TABLE produtoPreco ADD CONSTRAINT fkprodutoProduto FOREIGN KEY (produto) REFERENCES produto (oid) MATCH FULL;

ALTER TABLE produtoPreco ADD CONSTRAINT fkprodutoPreco FOREIGN KEY (listaPreco) REFERENCES listaPreco (oid) MATCH FULL;

create table tipoAdicional(
  oid character(32) primary key,
  nome varchar(50),
  exclusivo int
);

create table adicional(
  oid character(32) primary key,
  tipoAdicional character(32),
  nome varchar(100),
  descricao varchar(500),
  ativo int
);

ALTER TABLE adicional ADD CONSTRAINT fkadicionalTipoAdicional FOREIGN KEY (tipoAdicional) REFERENCES tipoAdicional (oid) MATCH FULL;

