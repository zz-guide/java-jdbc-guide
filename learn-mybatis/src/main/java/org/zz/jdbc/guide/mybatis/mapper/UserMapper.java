package org.zz.jdbc.guide.mybatis.mapper;

import org.zz.domain.User;

import java.util.List;

public interface UserMapper {
    public int insert(User user);

    public int batchInsert(List<User> users);

    public int deleteById(Long id);
    public int deleteByIds(Long[] ids);
    public int updateById(User user);

    public List<User> getList();
    public User getById(Long id);
    public List<User> getByIds(Long[] ids);

    public User getByIdWithLeftJoin(Long id);
}

