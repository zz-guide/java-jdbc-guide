package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testFindOne(){
        Logger logger = Logger.getLogger("testFindOne");

        long id = 1;
        User user = userMapper.getById(id);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
    }

    @Test
    void testInQuery(){
        Logger logger = Logger.getLogger("testInQuery");

        Long[] ids = {1L, 2L, 3L};
        List<User> users = userMapper.getByIds(ids);
        if (users == null || users.isEmpty()) {
            logger.log(Level.INFO, "查询结果为空");
            return;
        }

        for(User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
        }
    }

    @Test
    void testFindAll(){
        Logger logger = Logger.getLogger("testFindAll");

        List<User> users = userMapper.getList();
        for (User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
        }
    }

    @Test
    void testPageQuery(){
        Logger logger = Logger.getLogger("testPageQuery");
    }
}