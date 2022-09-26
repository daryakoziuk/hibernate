package com.dmdev.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "roles")
public class Role extends Entity{

    @Column(name = "name")
    private String name;
}
