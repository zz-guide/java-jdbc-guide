package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Student;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestJCommonsDbUtilsQuery {

    @Test
    public void testGetOne() {
        long id = 11L;
        String sql = "SELECT * from `student` where `id` = ?";

        Connection connection = null;
        QueryRunner qr = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            // 通过以下这个类转换字段和实体类属性映射关系，默认是下划线 驼峰方式匹配
            BasicRowProcessor basicRowProcessor = new BasicRowProcessor(new GenerousBeanProcessor());
            ResultSetHandler<Student> rsh = new BeanHandler<>(Student.class, basicRowProcessor);

            Student student = qr.query(connection, sql, rsh, id);
            if (student == null) {
                System.out.println("学生不存在");
            } else {
                System.out.println("查询到的结果:"+student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }

    @Test
    public void testFind() {
        // 待查询的ID列表
        List<Integer> ids = Arrays.asList(1,2,3,4);
        String inClause = ids.stream().map(id -> "?").collect(Collectors.joining(","));
        // 构造SQL查询语句
        String sql = "SELECT * FROM `student` WHERE `id` IN (" + inClause + ")";

        Connection connection = null;
        QueryRunner qr = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            BasicRowProcessor basicRowProcessor = new BasicRowProcessor(new GenerousBeanProcessor());
            ResultSetHandler<List<Student>> rsh = new BeanListHandler<>(Student.class, basicRowProcessor);

            List<Student> students = qr.query(connection, sql, rsh, ids.toArray());
            if (students.isEmpty()) {
                System.out.println("学生不存在");
            } else {
                System.out.println("查询到的结果:"+students);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
