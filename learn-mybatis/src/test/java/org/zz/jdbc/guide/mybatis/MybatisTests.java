package org.zz.jdbc.guide.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.domain.User;
import org.zz.util.MybatisUtils;


class MybatisTests {

    SqlSession session = MybatisUtils.getSession();

    @Test
    void testBeanDefaultValue(){
        User user = new User();
        System.out.println(user);
    }
}