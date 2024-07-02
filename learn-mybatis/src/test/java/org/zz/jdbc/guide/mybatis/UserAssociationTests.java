package org.zz.jdbc.guide.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.domain.User;
import org.zz.mapper.UserMapper;
import org.zz.util.MybatisUtils;


class UserAssociationTests {

    SqlSession session = MybatisUtils.getSession();

    @Test
    void testLeftJoin() {
        UserMapper mapper = session.getMapper(UserMapper.class);
        Long userId = 14L;
        User user = mapper.getByIdWithLeftJoin(userId);
        if (user == null) {
            System.out.println("用户数据为空");
            return;
        }

        System.out.println("user:" + user);
        System.out.println("user_orders:" + user.getOrders());
    }
}