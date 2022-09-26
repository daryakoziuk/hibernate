package com.dmdev.service.util;

import com.dmdev.service.entity.Car;
import com.dmdev.service.entity.Request;
import com.dmdev.service.entity.Role;
import com.dmdev.service.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration()
                .addPackage("com.dmdev")
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Request.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(User.class)
                .configure()
                .buildSessionFactory();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
