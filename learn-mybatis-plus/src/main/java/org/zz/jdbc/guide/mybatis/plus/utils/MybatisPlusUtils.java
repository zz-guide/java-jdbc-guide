package org.zz.jdbc.guide.mybatis.plus.utils;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.zz.jdbc.guide.mybatis.plus.mapper.UserMapper;

import javax.sql.DataSource;

public class MybatisPlusUtils {
    public static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            DataSource dataSource = dataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("Production", transactionFactory, dataSource);
            MybatisConfiguration configuration = new MybatisConfiguration(environment);
            configuration.addMapper(UserMapper.class);
            configuration.setLogImpl(StdOutImpl.class);

            // 分页拦截器
            PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();

            // MybatisPlusInterceptor
            MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
            mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

            configuration.addInterceptor(mybatisPlusInterceptor);

            sqlSessionFactory =new MybatisSqlSessionFactoryBuilder().build(configuration);
            System.out.println("连接成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource dataSource() {
        PooledDataSource dataSource = new PooledDataSource();
//        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setDriver("com.p6spy.engine.spy.P6SpyDriver");
//        dataSource.setUrl("jdbc:mysql://192.168.200.253:3306/jdbc?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8");
        dataSource.setUrl("jdbc:p6spy:mysql://192.168.200.253:3306/jdbc?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
