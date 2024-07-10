package org.zz.jdbc.guide.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateUtil {

    private static final Configuration configuration;
    private static final SessionFactory sessionFactory;

    static {
        try {
            configuration = new Configuration().configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();

            System.out.println("连接成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
}
