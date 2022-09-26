create table cars
(
    id     serial
        primary key,
    model  varchar(128) not null,
    status varchar(128) not null
);

alter table cars
    owner to postgres;

create table users
(
    id        serial
        primary key,
    name      varchar(128) not null,
    last_name varchar(128) not null,
    role_id   integer      not null
);

alter table users
    owner to postgres;

create table requests
(
    id           serial
        primary key,
    passport     varchar(128) not null,
    date_request date         not null,
    date_return  date         not null,
    user_id      integer
        references users
            on update cascade on delete cascade,
    car_id       integer
        references cars
            on update cascade on delete cascade
);

alter table requests
    owner to postgres;

create table roles
(
    id   serial
        primary key,
    name varchar(128) not null
);

alter table roles
    owner to postgres;


