package org.zz.jdbc.guide.mybatis.xml.user;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.model.UserModel;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserAssociationMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserNestedSelectQuery {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserAssociationMapper userAssociationMapper = session.getMapper(UserAssociationMapper.class);

    @Test
    void testUserOrderList(){
        Logger logger = Logger.getLogger("testUserOrderList-一对多");

        // 查询部分用户列表，并关联订单信息
        Long[] userIds = {1L, 2L, 7L};
        List<UserModel> userModels = userAssociationMapper.getByIds(userIds);
        if (userModels == null || userModels.isEmpty()) {
            logger.log(Level.INFO, "订单数据为空");
            return;
        }

        for (UserModel userModel : userModels) {
            logger.log(Level.INFO, "userModel 对象: {0}", new Object[]{userModel.toString()});
            logger.log(Level.INFO, "userModel 对象中的 order 列表: {0}", new Object[]{userModel.getOrders()});
        }
    }
}