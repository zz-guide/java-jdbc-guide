package org.zz.jdbc.guide.mybatis.xml.mapper;

import org.zz.jdbc.guide.common.model.UserModel;

import java.util.List;

public interface UserAssociationMapper {
    // === 查 ===
    public UserModel getById(Long id);

    public List<UserModel> getByIds(Long[] ids);

    public List<UserModel> getList();


    public List<UserModel> getListLeftJoinOrder();
}

