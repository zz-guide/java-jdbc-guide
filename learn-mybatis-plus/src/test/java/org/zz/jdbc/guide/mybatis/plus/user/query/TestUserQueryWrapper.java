package org.zz.jdbc.guide.mybatis.plus.user.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.mybatis.plus.entity.User;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.plus.utils.MybatisPlusUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserQueryWrapper {
    SqlSession session = MybatisPlusUtils.getSession();
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testAllCondition(){
        Logger logger = Logger.getLogger("testAllCondition");

        long userId = 1;
        QueryWrapper<User> queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("id", 1); // =
        queryWrapper.ne("id", 2); // !=, <>
        queryWrapper.ge("id", 3); // >=
        queryWrapper.gt("id", 4); // >
        queryWrapper.le("id", 5); // <=
        queryWrapper.lt("id", 6); // <
        queryWrapper.notBetween("id", 7, 8); // not between x and x
        queryWrapper.like("id", "9"); // like "%x%"
        queryWrapper.notLike("id", "10"); // not like "%x%"
        queryWrapper.likeLeft("id", "11"); // like "%x"
        queryWrapper.likeRight("id", "12"); // like "x%"
        queryWrapper.notLikeLeft("id", "13"); // not like "x%"
        queryWrapper.notLikeRight("id", "14"); // not like "x%"
        queryWrapper.isNull("id"); // id is null
        queryWrapper.in("id", 1,2,3); // in (1,2,3)
        queryWrapper.notIn("id", 1,2,3); // not in (1,2,3)
        queryWrapper.inSql("id", "select user_id from orders where id < 3"); // 高级in, 支持子查询
        queryWrapper.eqSql("id", "select MAX(user_id) from order"); // 高级 = , 支持子查询
        queryWrapper.groupBy("id", "name").having("sum(age) > {0}", 10);; // group by x having
        queryWrapper.orderByAsc("id"); // order by x asc

        User user = userMapper.selectOne(queryWrapper);

        logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user});
    }
}