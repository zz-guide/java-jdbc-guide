package org.zz.jdbc.guide.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.common.utils.MyJDBCUtil;

import java.sql.Connection;

public class TestJDBCConnection {

    @Test
    public void testConnection(){
        Connection conn = MyJDBCUtil.getConnection();
        Assertions.assertNotNull(conn);
    }
}
