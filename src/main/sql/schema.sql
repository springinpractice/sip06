drop database if exists sip06;
create database sip06;
use sip06;

create table account (
    id bigint unsigned not null auto_increment primary key,
    username varchar(50) unique not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    password varchar(64), -- varchar(64) to accommodate SHA-256 hashes
    marketing_ok boolean not null,
    accept_terms boolean not null,
    enabled boolean not null,
    date_created timestamp default 0,
    date_modified timestamp default current_timestamp on update current_timestamp,
    unique index account_idx_1 (username),
    unique index account_idx_2 (email)
) engine = InnoDb;

create table role (
    id bigint unsigned not null auto_increment primary key,
    name varchar(50) not null unique
) engine = InnoDb;

create table account_role (
    id bigint unsigned not null auto_increment primary key,
    account_id bigint unsigned not null,
    role_id bigint unsigned not null,
    foreign key (account_id) references account (id),
    foreign key (role_id) references role(id),
    unique index account_role_idx_1 (account_id, role_id)
) engine = InnoDb;
