package org.zz.jdbc.guide.mybatis.xml.order;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Order;
import org.zz.jdbc.guide.mybatis.xml.mapper.OrderMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.List;

class TestOrderAssociationQuery {
    SqlSession session = MybatisUtils.getSession();

    @Test
    void testOneToOne() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Order> orders = mapper.getList();
        if (orders == null || orders.isEmpty()) {
            System.out.println("订单数据为空");
            return;
        }

        for (Order order : orders) {
//            System.out.println("order_id:" + order.getId());
//            System.out.println("order_sn:" + order.getSn());
            System.out.println("order:" + order);
        }
    }

    @Test
    void testLeftJoin() {
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Order> orders = mapper.getListWithLeftJoin();
        if (orders == null || orders.isEmpty()) {
            System.out.println("订单数据为空");
            return;
        }

        for (Order order : orders) {
            System.out.println("order:" + order);
        }
    }
}