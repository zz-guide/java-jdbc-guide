package org.zz.jdbc.guide.mybatis.annotation.mapper;


import org.zz.jdbc.guide.common.entity.User;

import java.util.List;

public interface UserMapper {
    // === 增 ===
    public int insert(User user);
    public int batchInsert(List<User> users);

    // === 删 ===
    public int deleteById(Long id);
    public int deleteByIds(Long[] ids);

    // === 改 ===
    public int updateById(User user);
    public int updateByIdAlias(User user);

    // === 查 ===
    public User getById(Long id);
    public List<User> getByIds(Long[] ids);
    public List<User> getList();
    public List<User> getPageList(Integer currentPage, Integer pageSize);
    public User getByIdWithLeftJoin(Long id);
}

