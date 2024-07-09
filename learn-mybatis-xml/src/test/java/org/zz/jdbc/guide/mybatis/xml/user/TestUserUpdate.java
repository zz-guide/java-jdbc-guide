package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserUpdate {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testUpdateById() {
        Logger logger = Logger.getLogger("testUpdateById");

        long id = 5;
        User user = userMapper.getById(id);
        if (user == null) {
            System.out.println("查询失败，请重试");
            logger.log(Level.INFO, "查询失败，请重试");
            return;
        }

        user.setName("修改过的:" + user.getName());
        int affectRows = userMapper.updateById(user);
        session.commit();
        logger.log(Level.INFO, "update user by id affectRows: {0}", new Object[]{affectRows});
    }
}