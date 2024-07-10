package org.zz.jdbc.guide.mybatis.plus.user.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserUpdateWrapper {
    SqlSession session = MybatisPlusUtils.getSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testUpdateWrapper(){
        Logger logger = Logger.getLogger("testUpdateWrapper");

        long userId = 11;
        UpdateWrapper<User> query = Wrappers.update();
        query.eq("id", userId);
        query.set("name", "修改过的_用户_10");
        query.set("updated_at", LocalDateTime.now());
        int affectedRows = userMapper.update(query);
        logger.log(Level.INFO, "update user affectedRows: {0}", new Object[]{affectedRows});

        User user = userMapper.selectById(userId);
        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }

    @Test
    void testLambdaQueryWrapperUpdate(){
        Logger logger = Logger.getLogger("testLambdaQueryWrapperUpdate");

        long userId = 11;
        // QueryWrapper 同理
        LambdaQueryWrapper<User> query = Wrappers.lambdaQuery();
        query.eq(User::getId, userId);

        User user = new User();
        user.setId(userId);
        user.setName("修改过的_用户_10");
        user.setUpdatedAt(LocalDateTime.now());

        int affectedRows = userMapper.update(user, query);
        logger.log(Level.INFO, "update user affectedRows: {0}", new Object[]{affectedRows});

        User newUser = userMapper.selectById(userId);
        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{newUser});
    }
}