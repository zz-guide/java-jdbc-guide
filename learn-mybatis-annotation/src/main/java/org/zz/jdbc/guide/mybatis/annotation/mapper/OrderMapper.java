package org.zz.jdbc.guide.mybatis.annotation.mapper;


import org.zz.jdbc.guide.common.entity.Order;

import java.util.List;

public interface OrderMapper {
    public int insert(Order order);
    public int batchInsert(List<Order> orders);
    public Order getById(Long id);
    public List<Order> getListByUserId(Long userId);
    public List<Order> getList();
    public List<Order> getListWithLeftJoin();
}
