-- create database testtask;
CREATE SCHEMA IF NOT EXISTS db_schema;
create table if not exists db_schema.users
(
    id bigint primary key,
    username varchar(255) not null unique,
    password varchar(255) not null,
    name varchar(255),
    surname varchar(255),
    role varchar(255),
    location varchar(255)
);

create table if not exists db_schema.orders
(
    id bigint primary key,
    user_id bigint references db_schema.users(id),
    status varchar(255) not null,
    created timestamp
);

create table if not exists db_schema.product
(
    id bigint primary key,
    title varchar(255) not null,
    quantity bigint,
    price decimal
);

create table if not exists db_schema.goods
(
    id bigint primary key,
    title varchar(255) not null,
    quantity bigint,
    price decimal
);

create table if not exists db_schema.orders_goods
(
    id bigint primary key,
    order_id BIGINT references db_schema.orders(id),
    good_id BIGINT references db_schema.goods(id)
);

INSERT INTO db_schema.users (id, username, password, role, name, surname, location) VALUES (1, 'admin', '12345', 'ROLE_ADMIN', 'Vadim', 'Vadimov', 'Moscow');