package com.dmdev.service.integration;

import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.Status;
import com.dmdev.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CarIT extends IntegrationTestBase {

    private Session session = HibernateUtil.getSession();

    @Test
    void checkSaveCar() {
        session.beginTransaction();
        session.save(TestUtil.car);
        List resultList = session.createSQLQuery("select model, status from cars").getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(3);
    }

    @Test
    void checkUpdateCar() {
        session.beginTransaction();
        Car car = session.get(Car.class, 1L);
        car.setStatus(Status.USED);
        session.merge(car);
        Car updateCar = session.get(Car.class, 1L);
        session.getTransaction().commit();

        assertThat(updateCar.getStatus()).isEqualTo(Status.USED);
    }

    @Test
    void checkDeleteCar() {
        session.beginTransaction();
        Car car = session.get(Car.class, 1L);

        session.delete(car);
        session.flush();
        List resultList = session.createSQLQuery("select model, status from cars").getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(1);
    }

    @Test
    void checkGetCar() {
        session.beginTransaction();

        Car car = session.get(Car.class, 1L);
        session.getTransaction().commit();

        assertThat(car.getId()).isEqualTo(1L);
    }
}
