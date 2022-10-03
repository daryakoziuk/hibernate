package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.TypeFuel;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.dmdev.service.TestUtil.EXAMPLE_INTEGER_ID;
import static com.dmdev.service.TestUtil.EXAMPLE_LONG_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarCharacteristicIT {

    @Test
    void checkSaveCarCharacteristic() {
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
    void checkUpdateCarCharacteristic() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            CarCharacteristic carCharacteristic2 = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);
            carCharacteristic2.setTypeFuel(TypeFuel.PETROL);

            session.merge(carCharacteristic2);
            session.flush();
            session.clear();
            CarCharacteristic actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertEquals(carCharacteristic2.getTypeFuel(), actual.getTypeFuel());
        }
    }

    @Test
    void checkDeleteCarCharacteristic() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            Car carForDelete = session.get(Car.class, EXAMPLE_LONG_ID);

            session.delete(carForDelete);
            session.flush();
            var actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertThat(actual).isNull();
        }
    }

    @Test
    void checkGetCarCharacteristic() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);

            CarCharacteristic actual = session.get(CarCharacteristic.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertThat(actual.getId()).isNotNull();
            assertThat(actual.getId()).isEqualTo(EXAMPLE_INTEGER_ID);
        }
    }
}

