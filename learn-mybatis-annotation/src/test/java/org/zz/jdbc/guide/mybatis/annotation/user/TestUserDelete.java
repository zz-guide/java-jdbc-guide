package org.zz.jdbc.guide.mybatis.annotation.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.annotation.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.annotation.utils.MybatisUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserDelete {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testDeleteById() {
        Logger logger = Logger.getLogger("testDeleteById");

        long id = 3;
        User user = userMapper.getById(id);
        if (user == null) {
            logger.log(Level.INFO, "用户不存在");
            return;
        }

        int affectRows = userMapper.deleteById(user.getId());
        session.commit();
        logger.log(Level.INFO, "delete user by id affectRows: {0}", new Object[]{affectRows});
    }

    @Test
    void testDeleteByIds() {
        Logger logger = Logger.getLogger("testDeleteByIds");
        Long[] ids = new Long[]{1L, 2L, 3L};
        if (ids.length == 0) {
            logger.log(Level.INFO, "ids 数量 不能为空");
            return;
        }

        int affectRows = userMapper.deleteByIds(ids);
        session.commit();
        logger.log(Level.INFO, "delete user by ids affectRows: {0}", new Object[]{affectRows});
    }
}