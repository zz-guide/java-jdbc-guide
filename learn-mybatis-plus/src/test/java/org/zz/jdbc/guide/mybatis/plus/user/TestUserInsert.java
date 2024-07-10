package org.zz.jdbc.guide.mybatis.plus.user;

import com.baomidou.mybatisplus.core.batch.MybatisBatch;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserInsert {
    SqlSession session = MybatisPlusUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testInsert() {
        Logger logger = Logger.getLogger("testInsert");

        // 准备好要插入的 user 对象数据
        User user = User.builder().name("测试用户").username("test").password("123456").build();

        // 调用 mapper 中定义的插入方法
        int affectRows = userMapper.insert(user);
        session.commit();
        // 相比之下，只填充了 主键 字段
        logger.log(Level.INFO, "insert user affectRows: {0}", new Object[]{affectRows});
        logger.log(Level.INFO, "插入的 user 对象: {0}", new Object[]{user});

        if (affectRows > 0) {
            User newUser = userMapper.selectById(user.getId());
            logger.log(Level.INFO, "通过返回的主键id查询user: {0}", new Object[]{newUser.toString()});
        }
    }

    @Test
    void testBatchInsert() {
        Logger logger = Logger.getLogger("testBatchInsert");

        // 准备一会要批量插入的数据
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int sn = i + 1;
            User user = User.builder().name("用户_" + sn).username("test_" + sn).password("123456").build();
            users.add(user);
        }


        MybatisBatch<User> mybatisBatch = new MybatisBatch<>(MybatisPlusUtils.sqlSessionFactory, users);
        MybatisBatch.Method<User> method = new MybatisBatch.Method<>(UserMapper.class);
        mybatisBatch.execute(method.insert());
        for (User user : users) {
            // 相比之下，只填充了 主键 字段
            logger.log(Level.INFO, "批量插入的 user 对象: {0}", new Object[]{user.toString()});
        }
    }
}