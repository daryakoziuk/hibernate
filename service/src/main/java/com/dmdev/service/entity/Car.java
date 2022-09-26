package com.dmdev.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "cars")
public class Car extends Entity{

    @Column(name = "model")
    private String model;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
