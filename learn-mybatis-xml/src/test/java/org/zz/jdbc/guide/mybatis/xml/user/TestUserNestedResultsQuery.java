package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.model.UserModel;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserAssociationMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserNestedResultsQuery {
    SqlSession session = MybatisUtils.getSession();
    UserAssociationMapper userAssociationMapper = session.getMapper(UserAssociationMapper.class);

    @Test
    void testGetListWithLeftJoin() {
        Logger logger = Logger.getLogger("testGetListWithLeftJoin-一对多");

        // 查询用户列表，并关联订单信息，采用join方式查询
        // left join 如果能映射多条的话，会有多条记录
        List<UserModel> userModels = userAssociationMapper.getListLeftJoinOrder();
        if (userModels == null || userModels.isEmpty()) {
            logger.log(Level.INFO, "用户数据为空");
            return;
        }

        for (UserModel userModel : userModels) {
            logger.log(Level.INFO, "userModel 对象: {0}", new Object[]{userModel.toString()});
            logger.log(Level.INFO, "userModel 对象中的 order 对象: {0}", new Object[]{Arrays.toString(userModel.getOrders().toArray())});
        }
    }
}