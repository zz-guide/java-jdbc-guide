package org.zz.jdbc.guide.mybatis.xml;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.logging.Logger;


class TestSqlSession {
    @Test
    void testSqlSession(){
        Logger logger = Logger.getLogger("testSqlSession");

        SqlSession session = MybatisUtils.getSession();
        Assertions.assertNotNull(session);
    }
}