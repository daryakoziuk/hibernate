package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.Status;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.dmdev.service.TestUtil.EXAMPLE_LONG_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarIT {

    @Test
    void checkSaveCar() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            session.getTransaction().commit();

            assertThat(car.getId()).isNotNull();
        }
    }

    @Test
    void checkUpdateCar() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            Car car2 = session.get(Car.class, EXAMPLE_LONG_ID);
            car2.setStatus(Status.USED);

            session.merge(car2);
            session.flush();
            session.clear();
            Car actual = session.get(Car.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertEquals(car.getStatus(), actual.getStatus());
        }
    }

    @Test
    void checkDeleteCar() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            Car carForDelete = session.get(Car.class, EXAMPLE_LONG_ID);

            session.delete(carForDelete);
            session.flush();
            Car actual = session.get(Car.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(actual).isNull();
        }
    }

    @Test
    void checkGetCar() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);

            Car car2 = session.get(Car.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(car2.getId()).isNotNull();
            assertThat(car2.getId()).isEqualTo(EXAMPLE_LONG_ID);
        }
    }
}
