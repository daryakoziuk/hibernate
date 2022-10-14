package com.dmdev.service.dao;

import com.dmdev.service.dto.FilterCar;
import com.dmdev.service.dto.predicate.QPredicateCar;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.Status;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dmdev.service.entity.QCar.car;
import static com.dmdev.service.entity.QCarCharacteristic.carCharacteristic;

public class CarRepository extends BaseRepository<Long, Car> {

    private QPredicateCar predicateCar;

    public CarRepository(EntityManager entityManager) {
        super(entityManager, Car.class);
    }

    public List<Car> findCarByFilter(FilterCar filterCar) {
        return new JPAQuery<Car>(getEntityManager())
                .select(car)
                .from(car)
                .join(car.carCharacteristic, carCharacteristic)
                .where(predicateCar.builder(filterCar))
                .fetch();
    }

    public List<Car> findCarByStatus(Status status) {
        return new JPAQuery<Car>(getEntityManager())
                .select(car)
                .from(car)
                .where(car.status.eq(status))
                .fetch();
    }
}
