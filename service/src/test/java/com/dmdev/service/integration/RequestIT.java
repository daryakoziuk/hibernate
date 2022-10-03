package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.CarCharacteristic;
import com.dmdev.service.entity.Request;
import com.dmdev.service.entity.Tariff;
import com.dmdev.service.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.dmdev.service.TestUtil.EXAMPLE_DATE_REQUEST;
import static com.dmdev.service.TestUtil.EXAMPLE_DATE_RETURN;
import static com.dmdev.service.TestUtil.EXAMPLE_LONG_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestIT {

    @Test
    void checkSaveRequest() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Tariff tariff = TestUtil.getTariff();
            session.save(tariff);
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            User user = TestUtil.getUser();
            session.save(user);
            Request request = Request.builder()
                    .dateRequest(EXAMPLE_DATE_REQUEST)
                    .dateReturn(EXAMPLE_DATE_RETURN)
                    .build();
            request.setUser(user);
            request.setTariff(tariff);
            request.setCar(car);

            session.persist(request);
            session.getTransaction().commit();

            assertThat(request.getId()).isNotNull();
        }
    }

    @Test
    void checkUpdateRequest() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Tariff tariff = TestUtil.getTariff();
            session.save(tariff);
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            User user = TestUtil.getUser();
            session.save(user);
            Request request = Request.builder()
                    .dateRequest(EXAMPLE_DATE_REQUEST)
                    .dateReturn(EXAMPLE_DATE_RETURN)
                    .user(user)
                    .car(car)
                    .tariff(tariff)
                    .build();
            session.persist(request);
            Request requestForUpdate = session.get(Request.class, EXAMPLE_LONG_ID);
            requestForUpdate.setDateReturn(EXAMPLE_DATE_RETURN);

            session.update(requestForUpdate);
            session.flush();
            session.clear();
            Request updateRequest = session.get(Request.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertEquals(requestForUpdate.getDateReturn(), updateRequest.getDateReturn());
        }
    }

    @Test
    void checkDeleteRequest() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Tariff tariff = TestUtil.getTariff();
            session.save(tariff);
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            User user = TestUtil.getUser();
            session.save(user);
            Request request = Request.builder()
                    .dateRequest(EXAMPLE_DATE_REQUEST)
                    .dateReturn(EXAMPLE_DATE_RETURN)
                    .user(user)
                    .car(car)
                    .tariff(tariff)
                    .build();
            session.persist(request);
            Request requestForDelete = session.get(Request.class, EXAMPLE_LONG_ID);

            session.delete(requestForDelete);
            session.flush();
            session.clear();
            Request actual = session.get(Request.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(actual).isNull();
        }
    }

    @Test
    void checkGetRequest() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Tariff tariff = TestUtil.getTariff();
            session.save(tariff);
            Car car = TestUtil.getCar();
            CarCharacteristic carCharacteristic = TestUtil.getCarCharacteristic();
            carCharacteristic.setCar(car);
            session.save(car);
            User user = TestUtil.getUser();
            session.save(user);
            Request request = Request.builder()
                    .dateRequest(EXAMPLE_DATE_REQUEST)
                    .dateReturn(EXAMPLE_DATE_RETURN)
                    .user(user)
                    .car(car)
                    .tariff(tariff)
                    .build();
            session.persist(request);

            Request request2 = session.get(Request.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(request2.getId()).isNotNull();
            assertThat(request2.getId()).isEqualTo(EXAMPLE_LONG_ID);
        }
    }
}
