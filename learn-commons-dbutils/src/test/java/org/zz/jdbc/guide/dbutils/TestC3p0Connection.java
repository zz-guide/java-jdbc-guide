package org.zz.jdbc.guide.dbutils;

import org.junit.jupiter.api.Test;
import org.zz.jdbc.guide.dbutils.utils.MyC3p0Utils;
import org.zz.jdbc.guide.dbutils.utils.MyDruidUtils;

import java.sql.Connection;

public class TestC3p0Connection {

    @Test
    public void testConnection(){
        Connection conn = MyC3p0Utils.getConnection();
    }
}
