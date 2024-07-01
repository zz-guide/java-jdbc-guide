package org.zz;

import org.junit.jupiter.api.Test;
import org.zz.utils.MyJDBCUtil;

import java.sql.Connection;

public class TestJDBCConnection {

    @Test
    public void testConnection(){
        Connection conn = MyJDBCUtil.getConnection();
    }
}
