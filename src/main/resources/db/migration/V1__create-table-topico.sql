create table topico(
    id bigint not null auto_increment PRIMARY KEY,
    titulo varchar(100) not null,
    mensagem varchar(100) not null ,
    autor varchar(100) not null ,
    curso varchar(100) not null
);