package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.dto.predicate.QPredicate;
import com.dmdev.service.TestDatabaseImporter;
import com.dmdev.service.dto.FilterCar;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.Request;
import com.dmdev.service.entity.Status;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static com.dmdev.service.entity.QCar.car;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarIT {

    private static final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @BeforeAll
    static void initDb() {
        TestDatabaseImporter.insertDatabase(sessionFactory);
    }

    @AfterAll
    static void close() {
        sessionFactory.close();
    }

    @Test
    void checkQuerydslWithFilter() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        FilterCar filterCar = FilterCar.builder()
                .model(List.of("BMW", "Audi"))
                .dateRelease(LocalDate.of(2012, 1, 1))
                .build();
        Predicate predicate = QPredicate.builder()
                .add(filterCar.getDateRelease(), car.carCharacteristic.dateRelease::eq)
                .add(filterCar.getModel(), car.model::in)
                .buildAll();
        List<Car> cars = new JPAQuery<Request>(session)
                .select(car)
                .from(car)
                .where(predicate)
                .fetch();

        assertThat(cars).hasSize(1);
        session.getTransaction().rollback();
    }

    @Test
    void checkSaveCar() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car car = TestDatabaseImporter.getCar();
        CarCharacteristic carCharacteristic = TestDatabaseImporter.getCarCharacteristic();
        carCharacteristic.setCar(car);

        session.save(car);

        assertThat(car.getId()).isNotNull();
        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateCar() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car car = session.get(Car.class, 1L);
        car.setStatus(Status.USED);

        session.merge(car);
        session.flush();
        session.clear();
        Car actual = session.get(Car.class, car.getId());

        assertEquals(car.getStatus(), actual.getStatus());
        session.getTransaction().rollback();
    }

    @Test
    void checkDeleteCar() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car carForDelete = session.get(Car.class, 1L);

        session.delete(carForDelete);
        session.flush();
        Car actual = session.get(Car.class, carForDelete.getId());

        assertThat(actual).isNull();
        session.getTransaction().rollback();
    }

    @Test
    void checkGetCar() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = session.get(Car.class, 1L);

        assertThat(car.getId()).isNotNull();
        session.getTransaction().rollback();
    }
}
