package org.zz.jdbc.guide.dbutils;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.Connection;

public class TestDruidConnection {

    @Test
    public void testConnection(){
        Connection conn = MyDruidUtils.getConnection();
    }
}
