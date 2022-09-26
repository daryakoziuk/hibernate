package com.dmdev.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "users")
public class User extends Entity {

    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "role_id")
    private Integer roleId;
}
