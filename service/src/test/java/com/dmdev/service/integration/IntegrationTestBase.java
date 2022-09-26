package com.dmdev.service.integration;

import com.dmdev.service.TestUtil;
import com.dmdev.service.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;

public class IntegrationTestBase {

    private Session session;

    @BeforeEach
    void prepareDatabase(){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createNativeQuery(TestUtil.DELETE_ROLES).executeUpdate();
        session.createNativeQuery(TestUtil.CREATE_ROLES).executeUpdate();
        session.createNativeQuery(TestUtil.INSERT_ROLES).executeUpdate();
        session.createNativeQuery(TestUtil.DELETE_CARS).executeUpdate();
        session.createNativeQuery(TestUtil.CREATE_CARS).executeUpdate();
        session.createNativeQuery(TestUtil.INSERT_CARS).executeUpdate();
        session.createNativeQuery(TestUtil.DELETE_USERS).executeUpdate();
        session.createNativeQuery(TestUtil.CREATE_USERS).executeUpdate();
        session.createNativeQuery(TestUtil.INSERT_USERS).executeUpdate();
        session.createNativeQuery(TestUtil.DELETE_REQUESTS).executeUpdate();
        session.createNativeQuery(TestUtil.CREATE_REQUESTS).executeUpdate();
        session.createNativeQuery(TestUtil.INSERT_REQUESTS).executeUpdate();
        session.getTransaction().commit();
    }
}
