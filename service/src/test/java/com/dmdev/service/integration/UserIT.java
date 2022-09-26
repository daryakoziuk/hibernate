package com.dmdev.service.integration;

import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.User;
import com.dmdev.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIT extends IntegrationTestBase {

    private Session session = HibernateUtil.getSession();

    @Test
    void checkSaveUser() {
        session.beginTransaction();
        session.save(TestUtil.user);
        List<User> resultList = session.createSQLQuery("select u.last_name, u.name, u.role_id from users u").getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(3);
    }

    @Test
    void checkUpdateUser() {
        session.beginTransaction();
        User user = session.get(User.class, 1L);
        user.setLastname("Irishkova");

        session.merge(user);
        User updateUser = session.get(User.class, 1L);
        session.getTransaction().commit();

        assertThat(updateUser.getLastname()).isEqualTo("Irishkova");
    }

    @Test
    void checkDeleteUser() {
        session.beginTransaction();
        User user = session.get(User.class, 1L);

        session.delete(user);
        session.flush();
        List resultList = session.createSQLQuery("select u.last_name, u.name, u.role_id from users u")
                .getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(1);
    }

    @Test
    void checkGetUser() {
        session.beginTransaction();

        User user = session.get(User.class, 1L);
        session.getTransaction().commit();

        assertThat(user.getId()).isEqualTo(1L);
    }
}
