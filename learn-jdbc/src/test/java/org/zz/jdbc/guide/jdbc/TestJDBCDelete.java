package org.zz.jdbc.guide.jdbc;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.utils.MyJDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDBCDelete {

    @Test
    public void testDelete(){
        long id = 1L;
        String sql = "DELETE from `student` where `id` = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = MyJDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            int affectedRows  = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyJDBCUtil.close(connection, preparedStatement);
        }
    }
}
