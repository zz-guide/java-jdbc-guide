package org.zz.jdbc.guide.mybatis.xml.mapper;


import org.zz.jdbc.guide.common.model.OrderModel;

import java.util.List;

public interface OrderAssociationMapper {
    public List<OrderModel> getListByUserId(Long userId);
    public List<OrderModel> getList();
    public List<OrderModel> getListLeftJoinUser();
}
