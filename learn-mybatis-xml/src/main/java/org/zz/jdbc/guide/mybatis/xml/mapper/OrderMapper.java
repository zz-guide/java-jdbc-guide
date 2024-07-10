package org.zz.jdbc.guide.mybatis.xml.mapper;


import org.zz.jdbc.guide.common.entity.Order;

import java.util.List;

public interface OrderMapper {
    // === 增
    public int insert(Order order);
    public int batchInsert(List<Order> orders);
    // === 查
}
