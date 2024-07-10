package org.zz.jdbc.guide.hibernate;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.hibernate.utils.MyHibernateUtil;

import java.util.logging.Logger;


class TestSession {
    @Test
    void testSession(){
        Logger logger = Logger.getLogger("testSession");

        Session session = MyHibernateUtil.getSession();
        Assertions.assertNotNull(session);
    }
}