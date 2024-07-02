package org.zz.jdbc.guide.jdbc;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.utils.MyJDBCUtil;
import org.zz.jdbc.guide.common.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestJDBCQuery {

    @Test
    public void testGetOne() {
        long id = 1L;
        String sql = "SELECT * from `student` where `id` = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("resultSet.getRow():"+resultSet.getRow()); // 检索当前游标所在行号
            System.out.println("resultSet.isBeforeFirst():"+resultSet.isBeforeFirst());

            Student student = new Student();

            // 确定结果只有一条记录可以用if，否则就用while
            if (resultSet.next()) {
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
            }

            System.out.println("查询到的结果:"+student);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyJDBCUtil.close(connection, preparedStatement);
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
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // 设置参数
            for (int i = 0; i < ids.size(); i++) {
                preparedStatement.setLong(i+1, ids.get(i));
            }

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
