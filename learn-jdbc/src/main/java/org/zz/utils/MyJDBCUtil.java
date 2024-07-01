package org.zz.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MyJDBCUtil {
    private static final String driver;
    private static final String user;
    private static final String password;
    private static final String url;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = MyJDBCUtil.class.getResourceAsStream("/db.properties");
            properties.load(inputStream);

            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接数据库
     *
     * @return 一个数据库的连接
     */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭数据库连接
     *
     * @param resultSet  结果集
     * @param statement  用于执行sql语句的对象
     * @param connection 数据库连接
     *                   需要关闭资源就传入对象，否则传入null即可
     */
    public static void close(Statement statement, Connection connection, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement statement, Connection connection) {
        close(statement, connection, null);
    }

    /**
     * insert,update,delete统一操作
     *
     * @param sql sql语句
     * @param obj 参数
     * @return bool
     */
    public static boolean execute(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);

            // 根据位置设置参数
            for (int i = 1; i <= obj.length; i++) {
                ps.setObject(i, obj[i - 1]);
            }

            System.out.println("执行sql:" + sql);
            int res = ps.executeUpdate();

            System.out.println("ps.executeUpdate:"+res);
            System.out.println(":"+res);
            return res > 0;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            close(ps, conn, null);
        }

        return false;
    }
}
