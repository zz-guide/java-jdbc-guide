package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Student;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestJCommonsDbUtilsPageQuery {

    @Test
    public void testPageQuery() {
        // 分页查询参数
        int pageNumber = 1; // 当前页码
        int pageSize = 10; // 每页显示的记录数
        int offset = (pageNumber - 1) * pageSize; // 计算偏移量

        // 构造分页查询SQL查询语句
        String sql = "SELECT * FROM `student` LIMIT ?, ?";

        Connection connection = null;
        QueryRunner qr = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            BasicRowProcessor basicRowProcessor = new BasicRowProcessor(new GenerousBeanProcessor());
            ResultSetHandler<List<Student>> rsh = new BeanListHandler<>(Student.class, basicRowProcessor);

            List<Student> students = qr.query(connection, sql, rsh, offset, pageSize);
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
