package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.*;

public class TestCommonsDbUtilsDelete {

    @Test
    public void testDelete() {
        long id = 1L;
        String sql = "DELETE from `student` where `id` = ?";

        Connection connection = null;
        QueryRunner qr = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            int affectedRows = qr.update(connection, sql, id);
            if (affectedRows > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
