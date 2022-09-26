package com.dmdev.service.integration;

import com.dmdev.service.TestUtil;
import com.dmdev.service.entity.Request;
import com.dmdev.service.util.HibernateUtil;
import org.assertj.core.api.AssertionsForClassTypes;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestIT extends IntegrationTestBase {

    private Session session = HibernateUtil.getSession();

    @Test
    void checkSaveRequest() {
        session.beginTransaction();
        session.save(TestUtil.request);
        List resultList = session.createSQLQuery("select passport, date_request, date_return, user_id, car_id from requests")
                .getResultList();
        session.getTransaction().commit();

        assertThat(resultList).hasSize(2);
    }

    @Test
    void checkUpdateRequest() {
        session.beginTransaction();
        Request request = session.get(Request.class, 1L);
        request.setCarId(2);
        session.merge(request);
        Request updateRequest = session.get(Request.class, 1L);
        session.getTransaction().commit();

        AssertionsForClassTypes.assertThat(updateRequest.getCarId()).isEqualTo(2);
    }

    @Test
    void checkDeleteRequest() {
        session.beginTransaction();
        Request request = session.get(Request.class, 1L);
        session.delete(request);
        session.flush();
        List resultList = session.createSQLQuery("select passport, date_request, date_return, user_id, car_id from requests")
                .getResultList();
        session.getTransaction().commit();

        AssertionsForClassTypes.assertThat(resultList.size()).isEqualTo(0);
    }

    @Test
    void checkGetRequest() {
        session.beginTransaction();
        Request request = session.get(Request.class, 1L);
        session.getTransaction().commit();

        AssertionsForClassTypes.assertThat(request.getId()).isEqualTo(1L);
    }
}
