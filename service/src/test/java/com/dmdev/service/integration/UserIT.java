package com.dmdev.service.integration;

import com.dmdev.service.HibernateTestUtil;
import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static com.dmdev.service.TestUtil.EXAMPLE_LONG_ID;
import static com.dmdev.service.TestUtil.LASTNAME_FOR_UPDATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIT {

    @Test
    void checkSaveUser() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            User user = TestUtil.getUser();

            session.save(user);
            session.getTransaction().commit();

            assertThat(user.getId()).isNotNull();
        }
    }

    @Test
    void checkUpdateUser() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            session.save(TestUtil.getUser());
            User user = session.get(User.class, EXAMPLE_LONG_ID);
            user.getPersonalInfo().setLastname(LASTNAME_FOR_UPDATE);

            session.merge(user);
            session.flush();
            session.clear();
            User actualUser = session.get(User.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertEquals(user.getPersonalInfo().getLastname(), actualUser.getPersonalInfo().getLastname());
        }
    }

    @Test
    void checkDeleteUser() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            session.save(TestUtil.getUser());
            User user = session.get(User.class, EXAMPLE_LONG_ID);

            session.delete(user);
            session.flush();
            User actual = session.get(User.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(actual).isNull();
        }
    }

    @Test
    void checkGetUser() {
        try (Session session = HibernateTestUtil.getSession()) {
            session.beginTransaction();
            session.save(TestUtil.getUser());

            User user = session.get(User.class, EXAMPLE_LONG_ID);
            session.getTransaction().commit();

            assertThat(user.getId()).isNotNull();
            assertThat(user.getId()).isEqualTo(EXAMPLE_LONG_ID);
        }
    }
}
