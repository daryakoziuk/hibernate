package com.dmdev.service;

import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.Request;
import com.dmdev.service.entity.Role;
import com.dmdev.service.entity.Status;
import com.dmdev.service.entity.User;

import java.time.LocalDate;

public class TestUtil {

    public static final String DELETE_ROLES = """
            drop table if exists roles cascade ;
            """;
    public static final String CREATE_ROLES = """
            create table if not exists roles(
                id int auto_increment primary key ,
                name varchar(128) not null
            );
            """;
    public static final String INSERT_ROLES = """
            insert into roles (name) 
            values ( 'USER' ),
            ('ADMIN');
            """;
    public static final String DELETE_USERS = """
            drop table if exists users cascade ;
            """;
    public static final String CREATE_USERS = """
            create table if not exists users(
            id int auto_increment primary key ,
                name varchar(128) not null ,
                last_name varchar(128) not null ,
                role_id int not null references roles(id) on DELETE cascade on UPDATE cascade);
            """;
    public static final String INSERT_USERS = """
            insert into users (name, last_name, role_id)
            values ( 'ira', 'pirogova', 1 ),
            ('marta', 'mironova', 2);
            """;
    public static final String DELETE_CARS = """
            drop table if exists cars cascade ;
            """;
    public static final String CREATE_CARS = """
            create table if not exists cars(
                id int auto_increment primary key ,
                model varchar(128) not null,
                status varchar(128) not null
            );
            """;
    public static final String INSERT_CARS = """
            insert into cars (model, status)
            values ( 'audi', 'FREE' ),
            ('bmw', 'FREE');
            """;
    public static final String DELETE_REQUESTS = """
            drop table if exists requests cascade ;
            """;
    public static final String CREATE_REQUESTS = """
            create table if not exists requests(
                id int auto_increment primary key ,
                passport varchar(128) not null ,
                date_request date not null ,
                date_return date not null ,
                user_id int references users(id) on UPDATE cascade
                                        on DELETE cascade ,
                car_id int not null references cars (id) on DELETE cascade on UPDATE cascade
            );
            """;
    public static final String INSERT_REQUESTS = """
            insert into requests (passport, date_request, date_return, user_id, car_id)
            values ( 'KH1234', '2022-5-12', '2022-5-13', 1, 1 );
            """;

    public static final Role role = Role.builder()
            .name("MANAGER")
            .build();
    public static final Car car = Car.builder()
            .model("Audi")
            .status(Status.FREE)
            .build();
    public static final User user = User.builder()
            .lastname("Pypkova")
            .name("Olya")
            .roleId(1)
            .build();
    public static final Request request = Request.builder()
            .dateRequest(LocalDate.of(2022, 4, 4))
            .dateReturn(LocalDate.of(2022, 4, 6))
            .carId(1)
            .passport("KH12345")
            .userId(1)
            .build();
}
