package org.zz.jdbc.guide.mybatis.xml.order;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.model.OrderModel;
import org.zz.jdbc.guide.mybatis.xml.mapper.OrderAssociationMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestOrderNestedResultsQuery {
    SqlSession session = MybatisUtils.getSession();
    OrderAssociationMapper orderAssociationMapper = session.getMapper(OrderAssociationMapper.class);

    @Test
    void testGetListWithLeftJoin() {
        Logger logger = Logger.getLogger("testGetListWithLeftJoin-多对一");

        // 查询全部的订单列表，并关联用户信息，采用join方式查询
        List<OrderModel> orderModels = orderAssociationMapper.getListLeftJoinUser();
        if (orderModels == null || orderModels.isEmpty()) {
            logger.log(Level.INFO, "订单数据为空");
            return;
        }

        for (OrderModel orderModel : orderModels) {
            logger.log(Level.INFO, "orderModel 对象: {0}", new Object[]{orderModel.toString()});
            logger.log(Level.INFO, "orderModel 对象中的 user 对象: {0}", new Object[]{orderModel.getUser()});
        }
    }
}