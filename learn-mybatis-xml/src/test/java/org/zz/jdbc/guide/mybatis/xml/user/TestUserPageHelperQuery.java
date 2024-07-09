package org.zz.jdbc.guide.mybatis.xml.user;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserPageHelperQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class);

    @Test
    void testPageHelperPageQuery(){
        Logger logger = Logger.getLogger("testPageHelperPageQuery");
        PageHelper.offsetPage(1,4);
        // 紧跟在后的第一个select语句会进行分页查询
        List<User> users = userMapper.getPageListByPageHelper();
        for (User user : users) {
            logger.log(Level.INFO, "查询出来的user 对象: {0}", new Object[]{user.toString()});
        }
    }
}