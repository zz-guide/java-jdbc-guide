package org.zz.jdbc.guide.mybatis.annotation.order;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Order;
import org.zz.jdbc.guide.common.entity.User;
import org.zz.jdbc.guide.mybatis.annotation.mapper.OrderMapper;
import org.zz.jdbc.guide.mybatis.annotation.mapper.UserMapper;
import org.zz.jdbc.guide.mybatis.annotation.utils.MybatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
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
    void testInsert(){
        Logger logger = Logger.getLogger("testInsert");

        // 获取所有的user_id
        List<Long> userIds = getUserList().stream().map(User::getId).toList();

        List<Order> orders = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            Long randomUserId = getRandomListElement(userIds);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Order order = Order.builder().sn(uuid).userId(randomUserId).build();
            orders.add(order);
        }

        int affectRows = orderMapper.batchInsert(orders);
        session.commit();
        System.out.println("insertOrder affectRows:" + affectRows);
        for (Order order : orders) {
            System.out.println("order:" + order);
        }
    }
}