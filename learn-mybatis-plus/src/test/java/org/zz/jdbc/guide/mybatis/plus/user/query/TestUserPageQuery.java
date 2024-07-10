package org.zz.jdbc.guide.mybatis.plus.user.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserPageQuery {
    SqlSession session = MybatisPlusUtils.getSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testPageQuery() {
        Logger logger = Logger.getLogger("testPageQuery");

        QueryWrapper<User> queryWrapper = Wrappers.query();
        IPage<User> page = userMapper.selectPage(Page.of(1, 5), queryWrapper);

        List<User> users = page.getRecords();
        logger.log(Level.INFO, "分页总数量: {0}", new Object[]{page.getTotal()});
        logger.log(Level.INFO, "每页显示条数: {0}", new Object[]{page.getSize()});
        logger.log(Level.INFO, "数据项: {0}", new Object[]{users});

        for (User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
        }
    }
}