create table user
(
    id       integer
        primary key autoincrement,
    nome     varchar(64) not null,
    cognome  varchar(64) not null,
    email    varchar(64) not null,
    password varchar(64) not null
);

create table sensore
(

    id        integer primary key autoincrement,
    locazione varchar(128) not null


);

create table log_sensore
(

    id integer primary key autoincrement,
    inquinamento float not null ,
    temperatura float not null ,
    n_veicoli int not null,
    data_ora timestamp default current_timestamp,

    id_sensore int not null,

    foreign key (id_sensore) references sensore (id) on update cascade on delete cascade

);