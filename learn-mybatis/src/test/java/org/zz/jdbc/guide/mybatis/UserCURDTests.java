package org.zz.jdbc.guide.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zz.domain.User;
import org.zz.mapper.UserMapper;
import org.zz.util.MybatisUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class UserCURDTests {

    SqlSession session = MybatisUtils.getSession();

    @Test
    void testInsert(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = User.builder().name("仔仔").username("zz123456").password("123456").build();
        int affectRows = mapper.insert(user);
        session.commit();
        System.out.println("insertUser affectRows:" + affectRows);
        System.out.println("insertUser:" + user);

        if (affectRows > 0) {
            User newUser = mapper.getById(user.getId());
            System.out.println("再次查询user:" + newUser);
        }
    }

    @Test
    void testBatchInsert(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = new ArrayList<>();
        for (int i =0; i < 10; i++) {
            int sn = i+1;
            User user = User.builder().name("仔仔"+sn).username("zz"+sn).password("123456").build();
            users.add(user);
        }

        int affectRows = mapper.batchInsert(users);
        session.commit();
        System.out.println("insertUser affectRows:" + affectRows);
        for (User user : users) {
            System.out.println("user:" + user);
        }
    }

    @Test
    void testDeleteById(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        long id = 12;
        User user = mapper.getById(id);
        if (user == null) {
            System.out.println("查询失败，请重试");
            return;
        }

        int affectRows = mapper.deleteById(user.getId());
        // session.commit();
        System.out.println("deleteUserById affectRows:" + affectRows);
    }

    @Test
    void testDeleteByIds(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        Long[] ids = new Long[]{1L, 2L, 3L};
        int affectRows = mapper.deleteByIds(ids);
        // session.commit();
        System.out.println("deleteUserByIds affectRows:" + affectRows);
    }

    @Test
    void testUpdateById(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        long id = 12;
        User user = mapper.getById(id);
        if (user == null) {
            System.out.println("查询失败，请重试");
            return;
        }

        user.setName("修改过的:" + user.getName());
        int affectRows = mapper.updateById(user);
        System.out.println("updateUserById affectRows:" + affectRows);
    }

    @Test
    void testGetList(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.getList();
        if (!users.isEmpty()) {
            for (User user : users) {
                System.out.println(user);
                System.out.println(Objects.equals(user.getUsername(), ""));
            }
        }
    }

    @Test
    void testGetById(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        long id = 12;
        User user = mapper.getById(id);
        System.out.println("selectUserById:" + user);
    }

    @Test
    void testGetByIds(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        Long[] ids = {};
        List<User> users = mapper.getByIds(ids);
        if (users == null || users.isEmpty()) {
            System.out.println("ids 查询结果为空");
            return;
        }

        for(User user : users) {
            System.out.println("user:" + user);
        }
    }
}