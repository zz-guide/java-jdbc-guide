package org.zz;

import org.junit.jupiter.api.Test;
import org.zz.entity.Student;
import org.zz.utils.MyJDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestJDBCUpdate {

    @Test
    public void testUpdate() {
        // 要修改的student 对象
        Student stu = new Student();
        stu.setId(2L);
        stu.setSn("10000");
        stu.setName("仔仔1");
        stu.setUsername("zaizai");
        stu.setPassword("123456");
        stu.setSalt("123456");
        stu.setMobile("18888888888");
        stu.setGender(1);
        stu.setGrade("大一");
        stu.setBirthday(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        stu.setAge(30);
        stu.setUpdatedAt(LocalDateTime.now());

        String sql = "UPDATE `student` set sn=?, name=?, username=?, password=?, salt=?, mobile=?, gender=?, grade=?, birthday=?,age=?, updated_at=? where `id` = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stu.getSn());
            preparedStatement.setString(2, stu.getName());
            preparedStatement.setString(3, stu.getUsername());
            preparedStatement.setString(4, stu.getPassword());
            preparedStatement.setString(5, stu.getSalt());
            preparedStatement.setString(6, stu.getMobile());
            preparedStatement.setInt(7, stu.getGender());
            preparedStatement.setString(8, stu.getGrade());
            preparedStatement.setDate(9, Date.valueOf(stu.getBirthday()));
            preparedStatement.setInt(10, stu.getAge());
            preparedStatement.setTimestamp(11, Timestamp.valueOf(stu.getUpdatedAt()));
            preparedStatement.setLong(12, stu.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyJDBCUtil.close(preparedStatement, connection);
        }
    }
}
