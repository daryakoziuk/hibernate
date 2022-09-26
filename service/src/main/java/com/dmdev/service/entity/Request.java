package com.dmdev.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@javax.persistence.Entity
@Table(name = "requests")
public class Request extends Entity{

    @Column(name = "passport")
    private String passport;
    @Column(name = "date_request")
    private LocalDate dateRequest;
    @Column(name = "date_return")
    private LocalDate dateReturn;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "car_id")
    private Integer carId;
}
