package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.logging.Logger;

class TestUserAssociationQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testFindOne(){
        Logger logger = Logger.getLogger("testFindOne");

        Long userId = 14L;
        User user = userMapper.getByIdWithLeftJoin(userId);
        if (user == null) {
            System.out.println("用户数据为空");
            return;
        }

        System.out.println("user:" + user);
        System.out.println("user_orders:" + user.getOrders());

    }
}