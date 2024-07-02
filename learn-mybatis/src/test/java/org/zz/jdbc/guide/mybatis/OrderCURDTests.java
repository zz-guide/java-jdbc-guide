package org.zz.jdbc.guide.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.domain.Order;
import org.zz.domain.User;
import org.zz.mapper.OrderMapper;
import org.zz.mapper.UserMapper;
import org.zz.util.MybatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

class OrderCURDTests {

    SqlSession session = MybatisUtils.getSession();

    List<User> getUserList(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        return mapper.getList();
    }

    public static Long getRandomListElement(List<Long> list) {
        return list.stream()
                .skip(new Random().nextInt(list.size()))
                .findFirst()
                .orElse(null);
    }

    @Test
    void testInsert(){
        OrderMapper mapper = session.getMapper(OrderMapper.class);

        // 获取所有的user_id
        List<Long> userIds = getUserList().stream().map(User::getId).toList();

        List<Order> orders = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            Long randomUserId = getRandomListElement(userIds);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            Order order = Order.builder().sn(uuid).userId(randomUserId).build();
            orders.add(order);
        }

        int affectRows = mapper.batchInsert(orders);
        session.commit();
        System.out.println("insertOrder affectRows:" + affectRows);
        for (Order order : orders) {
            System.out.println("order:" + order);
        }
    }
}