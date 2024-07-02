package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.entity.Student;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestCommonsDbUtilsInsert {

    @Test
    public void testInsert() {
        Student stu = new Student();
        stu.setSn("10006");
        stu.setUsername("zaizai");
        stu.setName("仔仔");
        stu.setAge(30);
        stu.setGrade("大一");
        stu.setGender(1);
        stu.setMobile("18888888888");
        stu.setPassword("123456");
        stu.setSalt("123456");
        stu.setBirthday(Date.valueOf(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

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
        QueryRunner qr = new QueryRunner();
        Connection connection = null;
        try {
            connection = MyDruidUtils.getConnection();
            // 这里报错，提示java.math.BigInteger cannot be cast to java.lang.Long
            // 需要使用ScalarHandler<Number>

            ScalarHandler<Number> scalarHandler = new ScalarHandler<>();
            Number insert = qr.insert(connection, sql, scalarHandler, params.toArray());
            System.out.println("Generated ID: " + insert.longValue());
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        } finally {
            DbUtils.closeQuietly(connection);
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
            stu.setBirthday(Date.valueOf(LocalDate.parse("1996-06-13", DateTimeFormatter.ofPattern("yyyy-MM-dd"))));

            students.add(stu);
        }

        // 顺序和个数要对应
        String sql = "INSERT INTO `student`(`sn`,`name`,`age`,`username`,`password`,`salt`,`mobile`,`gender`,`grade`,`birthday`) values(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;
        List<Long> affectedRows = new ArrayList<>();

        List<Long> ids = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        List<Object[]> params = new ArrayList<>();

        try {
            connection = MyDruidUtils.getConnection();
            for (int i = 0; i < students.size(); i++) {
                Student stu = students.get(i);
                params.add(new Object[]{
                        stu.getSn(),
                        stu.getName(),
                        stu.getAge(),
                        stu.getUsername(),
                        stu.getPassword(),
                        stu.getSalt(),
                        stu.getMobile(),
                        stu.getGender(),
                        stu.getGrade(),
                        stu.getBirthday()}
                );

                // 每 100 条数据执行一次插入
                if (i != 0 && (i % 100 == 0 || i == students.size() - 1)) {
                    // batch 方法返回的受影响的行数
                    int[] res = queryRunner.batch(connection, sql,
                            params.toArray(new Object[params.size()][])
                    );

                    if (res.length > 0) {
                        System.out.println("插入成功");
                        affectedRows.addAll(Arrays.stream(res).mapToObj(Long::valueOf).toList());
                    } else {
                        System.out.println("插入失败");
                    }

                    params.clear();
                }
            }

            System.out.println("last insert ids:" + ids);
            System.out.println("affectedRows:"+ affectedRows);
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        } finally {
            DbUtils.closeQuietly(connection);
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        long timeSpent = endTime - startTime; // 计算程序执行时间
        System.out.println("程序执行时间：" + timeSpent + "毫秒");
    }

    @Test
    public void testBatchInsertWithPrimaryKey() {
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

        // 顺序和个数要对应
        String sql = "INSERT INTO `student`(`sn`,`name`,`age`,`username`,`password`,`salt`,`mobile`,`gender`,`grade`,`birthday`) values(?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;

        List<Long> ids = new ArrayList<>();
        QueryRunner queryRunner = new QueryRunner();
        List<Object[]> params = new ArrayList<>();

        try {
            connection = MyDruidUtils.getConnection();
            for (int i = 0; i < students.size(); i++) {
                Student stu = students.get(i);
                params.add(new Object[]{
                        stu.getSn(),
                        stu.getName(),
                        stu.getAge(),
                        stu.getUsername(),
                        stu.getPassword(),
                        stu.getSalt(),
                        stu.getMobile(),
                        stu.getGender(),
                        stu.getGrade(),
                        stu.getBirthday()}
                );

                // 每 100 条数据执行一次插入
                if (i != 0 && (i % 100 == 0 || i == students.size() - 1)) {
                    // insertBatch 返回自增主键
                    ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
                    List<Object[]> r = queryRunner.insertBatch(connection, sql, rsh,
                            params.toArray(new Object[params.size()][])
                    );

                    // 其实是 BigInteger 类型的，需要先转成Number,然后再转成包装类型
                    Number[] res = r.stream()
                            .flatMap(array -> Stream.of(array).filter(Number.class::isInstance).map(Number.class::cast))
                            .toArray(Number[]::new);

                    if (res.length > 0) {
                        System.out.println("插入成功");
                        ids.addAll(Arrays.stream(res).map(Number::longValue).toList());
                    } else {
                        System.out.println("插入失败");
                    }

                    params.clear();
                }
            }

            System.out.println("last insert ids:" + ids);
        } catch (SQLException e) {
            System.out.println("Exception:" + e);
        } finally {
            DbUtils.closeQuietly(connection);
        }

        long endTime = System.currentTimeMillis(); // 获取结束时间
        long timeSpent = endTime - startTime; // 计算程序执行时间
        System.out.println("程序执行时间：" + timeSpent + "毫秒");
    }
}
