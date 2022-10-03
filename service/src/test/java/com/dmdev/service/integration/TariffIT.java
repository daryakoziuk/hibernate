package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Tariff;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.dmdev.service.TestUtil.EXAMPLE_INTEGER_ID;
import static com.dmdev.service.TestUtil.PRICE_FOR_CHANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TariffIT {

    @Test
    void checkSaveTariff() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            Tariff tariff = TestUtil.getTariff();

            session.save(tariff);
            session.getTransaction().commit();

            assertThat(tariff.getId()).isNotNull();
        }
    }

    @Test
    void checkUpdateTariff() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            session.save(TestUtil.getTariff());
            Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);
            tariff.setPrice(PRICE_FOR_CHANGE);

            session.merge(tariff);
            session.flush();
            session.clear();
            Tariff actualTariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertEquals(tariff.getPrice(), actualTariff.getPrice());
        }
    }

    @Test
    void checkDeleteTariff() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            session.save(TestUtil.getTariff());
            Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);

            session.delete(tariff);
            session.flush();
            Tariff actual = session.get(Tariff.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertThat(actual).isNull();
        }
    }

    @Test
    void checkGetTariff() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();

            session.save(TestUtil.getTariff());
            Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);
            session.getTransaction().commit();

            assertThat(tariff.getId()).isNotNull();
            assertThat(tariff.getId()).isEqualTo(EXAMPLE_INTEGER_ID);
        }
    }
}
