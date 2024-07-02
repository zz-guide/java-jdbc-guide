package org.zz.jdbc.guide.jdbc;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.utils.MyJDBCUtil;
import org.zz.jdbc.guide.common.entity.Student;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestJDBCTransaction {
    @Test
    public void testTransaction() {
        //开启事务: setAutoCommit(boolean autoCommit) : 调用该方法设置参数为false,即开启事务
        //提交事务: commit()
        //回滚事务: rollback()

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
            stu.setBirthday(Date.valueOf(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

            students.add(stu);
        }

        String sql = "INSERT INTO `student`(`sn`,`name`,`age`,`username`,`password`,`salt`,`mobile`,`gender`,`grade`,`birthday`) values(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Integer> affectedRows = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        try {
            connection = MyJDBCUtil.getConnection();
            connection.setAutoCommit(false);
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

            connection.commit();
            System.out.println("last insert ids:" + ids);
            System.out.println("affectedRows:"+ affectedRows);
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("ex:"+ex);
            }

        } finally {
            MyJDBCUtil.close(connection, preparedStatement);
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        long timeSpent = endTime - startTime; // 计算程序执行时间
        System.out.println("程序执行时间：" + timeSpent + "毫秒");
    }
}
