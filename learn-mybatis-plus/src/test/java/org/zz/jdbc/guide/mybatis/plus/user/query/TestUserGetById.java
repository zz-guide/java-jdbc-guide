package org.zz.jdbc.guide.mybatis.plus.user.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserGetById {
    SqlSession session = MybatisPlusUtils.getSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testMapperSelectById(){
        Logger logger = Logger.getLogger("testMapperSelectById");

        long userId = 1;
        User user = userMapper.selectById(userId);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }

    @Test
    void testLambdaQueryWrapperEq(){
        Logger logger = Logger.getLogger("testLambdaQueryWrapperEq");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId, 1);
        User user = userMapper.selectOne(lambdaQueryWrapper);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }

    @Test
    void testLambdaQueryWrapperAllEq(){
        Logger logger = Logger.getLogger("testLambdaQueryWrapperAllEq");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.allEq(Map.of(User::getId, 1, User::getName, "测试用户"));
        User user = userMapper.selectOne(lambdaQueryWrapper);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }

    @Test
    void testQueryWrapperEq(){
        Logger logger = Logger.getLogger("testQueryWrapperEq");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", 1);
        User user = userMapper.selectOne(queryWrapper);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }

    @Test
    void testQueryWrapperAllEq(){
        Logger logger = Logger.getLogger("testQueryWrapperAllEq");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(Map.of("id", 1, "name", "测试用户"));
        User user = userMapper.selectOne(queryWrapper);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }
}