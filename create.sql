create table curso (id number(19,0) generated as identity, descricao varchar2(255 char), duracao varchar2(255 char) not null, link varchar2(255 char) not null, nome varchar2(50 char) not null, usuario_id number(19,0), primary key (id));
create table simulador (id number(19,0) generated as identity, aporte float(53) not null, juros float(53) not null, rendimento float(53) not null, tempo_invest number(10,0) not null, tipo_invest varchar2(255 char) not null, valor float(53) not null check (valor>=100), usuario_id number(19,0), primary key (id));
create table usuario (id number(19,0) generated as identity, cpf varchar2(11 char) not null, email varchar2(255 char) not null, nome varchar2(255 char) not null, senha varchar2(255 char) not null, telefone varchar2(255 char), primary key (id));
alter table curso add constraint FKqgls7v9osmi479bbgi2t9aaoq foreign key (usuario_id) references usuario;
alter table simulador add constraint FKochpmw13xdxxuvlpjoprg2ecy foreign key (usuario_id) references usuario;

