package org.zz.jdbc.guide.mybatis.xml.mapper;


import org.apache.ibatis.session.RowBounds;
import org.zz.jdbc.guide.common.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public User getByIdWithLeftJoin(Long id);

    public List<User> getPageList(Map<String,Integer> map);
    public List<User> getPageListByRowBounds(RowBounds rowBounds);

    // === 聚合查询 ===
    public Long getTotalCount();
    public List<Map<String, Objects>> getGroupByList();
    public List<User> getListByFieldOrder();

    // === pageHelper 插件相关方法 ===
    public List<User> getPageListByPageHelper();

}

