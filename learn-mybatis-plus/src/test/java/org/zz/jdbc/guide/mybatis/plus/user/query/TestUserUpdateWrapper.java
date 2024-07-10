package org.zz.jdbc.guide.mybatis.plus.user.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserUpdateWrapper {
    SqlSession session = MybatisPlusUtils.getSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testAllCondition(){
        Logger logger = Logger.getLogger("testAllCondition");
        QueryWrapper<Object> query = Wrappers.query();
        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{null});
    }
}