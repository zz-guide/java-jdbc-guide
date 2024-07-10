package org.zz.jdbc.guide.hibernate.user;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.hibernate.entity.User;
import org.zz.jdbc.guide.hibernate.utils.MyHibernateUtil;

import java.util.logging.Level;
import java.util.logging.Logger;

class TestUserInsert {
    Session session = MyHibernateUtil.getSession(); // 不自动提交

    @Test
    void testInsert(){
        Logger logger = Logger.getLogger("testInsert");

        Transaction transaction = session.beginTransaction();

        // 准备好要插入的 user 对象数据
        User user = User.builder().name("测试用户").username("test").password("123456").build();

        session.persist(user);
        transaction.commit();
        session.close();
        // 相比之下，只填充了 主键 字段
        logger.log(Level.INFO, "插入的 user 对象: {0}", new Object[]{user});

//        if (user.getId() > 0) {
//            logger.log(Level.INFO, "通过返回的主键id查询user: {0}", new Object[]{newUser.toString()});
//        }
    }

    @Test
    void testBatchInsert(){
        Logger logger = Logger.getLogger("testInsert");


    }
}