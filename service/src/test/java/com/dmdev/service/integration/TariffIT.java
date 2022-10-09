package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Tariff;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.dmdev.service.TestUtil.EXAMPLE_INTEGER_ID;
import static com.dmdev.service.TestUtil.PRICE_FOR_CHANGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TariffIT {

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
    void checkSaveTariff() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tariff tariff = TestUtil.getTariff();

        session.save(tariff);

        assertThat(tariff.getId()).isNotNull();
        session.getTransaction().rollback();
    }

    @Test
    void checkUpdateTariff() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);
        tariff.setPrice(PRICE_FOR_CHANGE);

        session.merge(tariff);
        session.flush();
        session.clear();
        Tariff actualTariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);

        assertEquals(tariff.getPrice(), actualTariff.getPrice());
        session.getTransaction().rollback();
    }

    @Test
    void checkDeleteTariff() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);

        session.delete(tariff);
        session.flush();
        Tariff actual = session.get(Tariff.class, EXAMPLE_INTEGER_ID);

        assertThat(actual).isNull();
        session.getTransaction().rollback();
    }

    @Test
    void checkGetTariff() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        Tariff tariff = session.get(Tariff.class, EXAMPLE_INTEGER_ID);

        assertThat(tariff.getId()).isNotNull();
        assertThat(tariff.getId()).isEqualTo(EXAMPLE_INTEGER_ID);
        session.getTransaction().rollback();
    }
}
