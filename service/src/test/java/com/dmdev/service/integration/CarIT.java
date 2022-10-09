package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.QPredicate;
import com.dmdev.service.TestUtil;
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
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.List;

import static com.dmdev.service.entity.QCar.car;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarIT {

    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @BeforeAll
    void initDb() {
        TestUtil.createDatabase(sessionFactory);
    }

    @AfterAll
    void close() {
        sessionFactory.close();
    }

    @Test
    void checkQuerydsl(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        FilterCar filterCar = FilterCar.builder()
                .model("BMW")
                .dateRelease(LocalDate.of(2012,01,01))
                .build();
        Predicate predicate = QPredicate.builder()
                .add(filterCar.getDateRelease(), car.carCharacteristic.dateRelease::eq)
                .add(filterCar.getModel(), car.model::eq)
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
        Car car = TestUtil.getCar();
        CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
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
