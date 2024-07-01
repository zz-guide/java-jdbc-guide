package org.zz;

import org.junit.jupiter.api.Test;
import org.zz.entity.Student;
import org.zz.utils.MyJDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJDBCInsert {

    @Test
    public void testInsert() {
        Student stu = new Student();
        stu.setSn("10000");
        stu.setUsername("zaizai");
        stu.setName("仔仔");
        stu.setAge(30);
        stu.setGrade("大一");
        stu.setGender(1);
        stu.setMobile("18888888888");
        stu.setPassword("123456");
        stu.setSalt("123456");
        stu.setBirthday(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        List<Object> params = new ArrayList<>();
        params.add(stu.getSn());
        params.add(stu.getName());
        params.add(stu.getAge());
        params.add(stu.getUsername());
        params.add(stu.getPassword());
        params.add(stu.getSalt());
        params.add(stu.getMobile());
        params.add(stu.getGender());
        params.add(stu.getGrade());
        params.add(stu.getBirthday());

        String sql = "INSERT INTO `student`(`sn`,`name`,`age`,`username`,`password`,`salt`,`mobile`,`gender`,`grade`,`birthday`) values(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 1; i <= params.size(); i++) {
                preparedStatement.setObject(i, params.get(i - 1));
            }

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println("affectedRows :" + affectedRows);
            if (affectedRows > 0) {
                System.out.println("student 创建成功");
            } else {
                System.out.println("student 创建失败");
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                System.out.println("last insert id:" + generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        } finally {
            MyJDBCUtil.close(preparedStatement, connection);
        }
    }

    @Test
    public void testBatchInsert() {
        long startTime = System.currentTimeMillis(); // 获取开始时间

        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            Student stu = new Student();
            stu.setSn(String.valueOf((10000 + i)));
            stu.setUsername("zaizai_" + i);
            stu.setName("仔仔_" + i);
            stu.setAge(30);
            stu.setGrade("大一");
            stu.setGender(1);
            stu.setMobile("18888888888");
            stu.setPassword("123456");
            stu.setSalt("123456");
            stu.setBirthday(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            students.add(stu);
        }

        String sql = "INSERT INTO `student`(`sn`,`name`,`age`,`username`,`password`,`salt`,`mobile`,`gender`,`grade`,`birthday`) values(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Integer> affectedRows = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            for (int i = 0; i < students.size(); i++) {
                Student stu = students.get(i);
                preparedStatement.setObject(1, stu.getSn());
                preparedStatement.setObject(2, stu.getName());
                preparedStatement.setObject(3, stu.getAge());
                preparedStatement.setObject(4, stu.getUsername());
                preparedStatement.setObject(5, stu.getPassword());
                preparedStatement.setObject(6, stu.getSalt());
                preparedStatement.setObject(7, stu.getMobile());
                preparedStatement.setObject(8, stu.getGender());
                preparedStatement.setObject(9, stu.getGrade());
                preparedStatement.setObject(10, stu.getBirthday());

                // 添加到集合
                preparedStatement.addBatch();

                // 每 100 条数据执行一次插入
                if (i != 0 && (i % 100 == 0 || i == students.size() - 1)) {
                    int[] res = preparedStatement.executeBatch();
                    System.out.println("批量插入结果:" + Arrays.toString(res));
                    if (res.length > 0) {
                        System.out.println("插入成功");
                        affectedRows.addAll(Arrays.stream(res).boxed().toList());

                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                        while (generatedKeys.next()) {
                            ids.add(generatedKeys.getLong(1));
                        }
                    } else {
                        System.out.println("插入失败");
                    }

                    preparedStatement.clearBatch();
                }
            }

            System.out.println("last insert ids:" + ids);
            System.out.println("affectedRows:"+ affectedRows);
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        } finally {
            MyJDBCUtil.close(preparedStatement, connection);
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        long timeSpent = endTime - startTime; // 计算程序执行时间
        System.out.println("程序执行时间：" + timeSpent + "毫秒");
    }
}
