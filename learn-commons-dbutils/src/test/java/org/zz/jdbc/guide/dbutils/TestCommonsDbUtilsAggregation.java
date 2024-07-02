package org.zz.jdbc.guide.dbutils;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TestCommonsDbUtilsAggregation {

    @Test
    public void testCount() {
        String sql = "SELECT count(*) from `student`";

        Connection connection = null;
        QueryRunner qr = new QueryRunner();
        try {
            connection = MyDruidUtils.getConnection();
            ResultSetHandler<Number> rsh = new ScalarHandler<>();

            Number res = qr.query(connection, sql, rsh);
            System.out.println("student 表数量:"+res);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
