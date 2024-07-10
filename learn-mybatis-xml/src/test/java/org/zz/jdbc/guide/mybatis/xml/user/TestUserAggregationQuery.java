package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserAggregationQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testCountQuery(){
        Logger logger = Logger.getLogger("testCountQuery");

        Long totalCount = userMapper.getTotalCount();
        logger.log(Level.INFO, "user 表总数量: {0}", new Object[]{totalCount});
    }

    @Test
    void testGroupByQuery(){
        Logger logger = Logger.getLogger("testGroupByQuery");

        List<Map<String, Objects>> groupByList = userMapper.getGroupByList();
        for (Map<String,Objects> user : groupByList) {
            logger.log(Level.INFO, "group by 数据: {0}", new Object[]{user});
        }
    }

    @Test
    void testCustomFieldQuery(){
        Logger logger = Logger.getLogger("testCustomFieldQuery");
        // ORDER BY FIELD() 自定义字段的值排序
        List<User> users = userMapper.getListByFieldOrder();
        for (User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
        }
    }
}