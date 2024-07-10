package org.zz.jdbc.guide.mybatis.xml.order;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Order;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.xml.mapper.OrderMapper;
import org.zz.jdbc.guide.mybatis.xml.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.xml.utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

class TestOrderInsert {
    SqlSession session = MybatisUtils.getSession(); // 不自动提交
    UserMapper userMapper = session.getMapper(UserMapper.class); // 先获取一个 mapper 对象

    OrderMapper orderMapper = session.getMapper(OrderMapper.class);


    List<User> getUserList(){
        return userMapper.getList();
    }

    public static Long getRandomListElement(List<Long> list) {
        return list.stream()
                .skip(new Random().nextInt(list.size()))
                .findFirst()
                .orElse(null);
    }

    @Test
    void testBatchInsert(){
        Logger logger = Logger.getLogger("testBatchInsert");

        // 获取所有的user_id
        List<Long> userIds = getUserList().stream().map(User::getId).toList();

        // 创建 n 个订单，用户随机选
        List<Order> orders = new ArrayList<>();
        for (int i =0; i < 20; i++) {
            Long randomUserId = getRandomListElement(userIds);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Order order = Order.builder().sn(uuid).userId(randomUserId).build();
            orders.add(order);
        }

        int affectRows = orderMapper.batchInsert(orders);
        session.commit();
        logger.log(Level.INFO, "batch insert orders affectRows: {0}", new Object[]{affectRows});
        for (Order order : orders) {
            logger.log(Level.INFO, "批量插入的 order 对象: {0}", new Object[]{order.toString()});
        }
    }
}