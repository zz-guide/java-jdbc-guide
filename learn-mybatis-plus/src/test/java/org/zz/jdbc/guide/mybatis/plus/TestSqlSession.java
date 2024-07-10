package org.zz.jdbc.guide.mybatis.plus;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.util.logging.Logger;


class TestSqlSession {
    @Test
    void testSqlSession(){
        Logger logger = Logger.getLogger("testSqlSession");

        SqlSession session = MybatisPlusUtils.getSession();
        Assertions.assertNotNull(session);
    }
}