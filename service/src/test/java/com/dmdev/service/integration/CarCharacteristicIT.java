package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.TypeFuel;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.dmdev.service.TestUtil.EXAMPLE_INTEGER_ID;
import static com.dmdev.service.TestUtil.EXAMPLE_LONG_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarCharacteristicIT {

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
    void checkSaveCarCharacteristic() {
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
    void checkUpdateCarCharacteristic() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        CarCharacteristic carCharacteristic2 = session.get(CarCharacteristic.class, 1);
        carCharacteristic2.setType(TypeFuel.PETROL);

        session.merge(carCharacteristic2);
        session.flush();
        session.clear();
        CarCharacteristic actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);

        assertEquals(carCharacteristic2.getType(), actual.getType());
        session.getTransaction().rollback();
    }

    @Test
    void checkDeleteCarCharacteristic() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Car carForDelete = session.get(Car.class, EXAMPLE_LONG_ID);

        session.delete(carForDelete);
        session.flush();
        var actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);

        assertThat(actual).isNull();
        session.getTransaction().rollback();
    }

    @Test
    void checkGetCarCharacteristic() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        CarCharacteristic actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);

        assertThat(actual.getId()).isNotNull();
        session.getTransaction().rollback();
    }
}

