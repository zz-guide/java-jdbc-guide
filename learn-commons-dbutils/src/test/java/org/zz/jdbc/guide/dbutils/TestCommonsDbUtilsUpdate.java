package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Student;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestCommonsDbUtilsUpdate {

    @Test
    public void testIUpdate() {
        // 要修改的student 对象
        Student stu = new Student();
        stu.setId(3L);
        stu.setSn("10000");
        stu.setName("仔仔1");
        stu.setUsername("zaizai");
        stu.setPassword("1234567");
        stu.setSalt("1234567");
        stu.setMobile("18888888889");
        stu.setGender(2);
        stu.setGrade("大二");
        stu.setBirthday(Date.valueOf(LocalDate.parse("1996-06-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        stu.setAge(32);
        stu.setUpdatedAt(LocalDateTime.now());

        List<Object> params = new ArrayList<>();
        params.add(stu.getSn());
        params.add(stu.getName());
        params.add(stu.getUsername());
        params.add(stu.getPassword());
        params.add(stu.getSalt());
        params.add(stu.getMobile());
        params.add(stu.getGender());
        params.add(stu.getGrade());
        params.add(stu.getBirthday());
        params.add(stu.getAge());
        params.add(stu.getUpdatedAt());
        params.add(stu.getId());

        String sql = "UPDATE `student` set sn=?, name=?, username=?, password=?, salt=?, mobile=?, gender=?, grade=?, birthday=?,age=?, updated_at=? where `id` = ?";

        Connection connection = null;
        QueryRunner queryRunner = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            int affectedRows = queryRunner.update(connection, sql, params.toArray());
            if (affectedRows > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
