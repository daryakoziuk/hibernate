package com.dmdev.service.integration;

import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Role;
import com.dmdev.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleIT extends IntegrationTestBase {

    private Session session = HibernateUtil.getSession();

    @Test
    void checkSaveRole() {
        session.beginTransaction();
        session.save(TestUtil.role);
        List resultList = session.createSQLQuery("select name from roles").getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(3);
    }

    @Test
    void checkUpdateRole() {
        session.beginTransaction();
        Role role = session.get(Role.class, 1L);
        role.setName("ALL");

        session.merge(role);
        Role updateRole = session.get(Role.class, 1L);
        session.getTransaction().commit();

        assertEquals(updateRole.getName(), "ALL");
    }

    @Test
    void checkDeleteRole() {
        session.beginTransaction();
        Role role = session.get(Role.class, 1L);

        session.delete(role);
        session.flush();
        List resultList = session.createSQLQuery("select name from roles").getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(1);
    }

    @Test
    void checkGetRole() {
        session.beginTransaction();
        Role role = session.get(Role.class, 1L);
        session.getTransaction().commit();

        assertThat(role.getId()).isEqualTo(1L);
    }
}
