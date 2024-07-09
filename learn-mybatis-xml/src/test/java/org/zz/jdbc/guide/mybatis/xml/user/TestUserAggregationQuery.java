package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserAggregationQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testCountQuery(){
        Logger logger = Logger.getLogger("testCountQuery");
        Map<String,Integer> map = new HashMap<>();
        map.put("offset", 1);
        map.put("pageSize", 5);

        List<User> users = userMapper.getPageList(map);
        for (User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
        }
    }
}