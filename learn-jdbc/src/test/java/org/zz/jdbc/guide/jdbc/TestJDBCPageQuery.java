package org.zz.jdbc.guide.jdbc;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Student;
import org.zz.jdbc.guide.common.utils.MyJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestJDBCPageQuery {

    @Test
    public void testPageQuery() {
        // 分页查询参数
        int pageNumber = 1; // 当前页码
        int pageSize = 10; // 每页显示的记录数
        int offset = (pageNumber - 1) * pageSize; // 计算偏移量

        // 构造分页查询SQL查询语句
        String sql = "SELECT * FROM `student` LIMIT ?, ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, pageSize);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setSn(resultSet.getString("sn"));
                student.setName(resultSet.getString("name"));
                student.setUsername(resultSet.getString("username"));
                student.setPassword(resultSet.getString("password"));
                student.setSalt(resultSet.getString("salt"));
                student.setAge(resultSet.getInt("age"));
                student.setGrade(resultSet.getString("grade"));
                student.setMobile(resultSet.getString("mobile"));
                student.setGender(resultSet.getInt("gender"));
                student.setBirthday(resultSet.getDate("birthday"));
                student.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                student.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());

                students.add(student);
            }

            System.out.println("查询结果数量:" + students.size());
            System.out.println("查询到的结果:"+ students);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyJDBCUtil.close(connection, preparedStatement);
        }
    }
}
