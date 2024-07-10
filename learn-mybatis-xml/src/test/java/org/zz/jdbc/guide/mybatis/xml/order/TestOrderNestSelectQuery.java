package org.zz.jdbc.guide.mybatis.xml.order;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.model.OrderModel;
import org.zz.jdbc.guide.mybatis.xml.mapper.OrderAssociationMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestOrderNestSelectQuery {
    SqlSession session = MybatisUtils.getSession();
    OrderAssociationMapper orderAssociationMapper = session.getMapper(OrderAssociationMapper.class);

    @Test
    void testGetACertainUserOrderList() {
        Logger logger = Logger.getLogger("testGetACertainUserOrderList-多对一");

        // 查询某个用户的订单列表，并关联用户信息，采用嵌套查询
        // 当代码中使用关联属性时，会自动查询
        long userId = 7;
        List<OrderModel> orderModels = orderAssociationMapper.getListByUserId(userId);
        if (orderModels == null || orderModels.isEmpty()) {
            logger.log(Level.INFO, "订单数据为空");
            return;
        }

        for (OrderModel orderModel : orderModels) {
            logger.log(Level.INFO, "orderModel 对象: {0}", new Object[]{orderModel.toString()});
            logger.log(Level.INFO, "orderModel 对象中的 user 对象: {0}", new Object[]{orderModel.getUser()});
        }
    }

    @Test
    void testGetAllOrderList() {
        Logger logger = Logger.getLogger("testGetAllOrderList-多对一");

        // 查询全部的订单列表
        // 经过测试发现，这种方法对于user表会执行N次查询，也就是俗称的 "N+1"查询
        // 虽然可以通过懒加载控制，但是数据量大的情况下，性能不太好，所以不建议生产环境使用
        List<OrderModel> orderModels = orderAssociationMapper.getList();
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