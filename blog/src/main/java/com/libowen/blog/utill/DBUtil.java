package com.libowen.blog.utill;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;

public class DBUtil {
    public static final DataSource dataSource;
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL("jdbc:mysql://localhost:3306/blog?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("libowen");
        dataSource = mysqlDataSource;
    }
    @SneakyThrows
    public static Connection connection(){
        return dataSource.getConnection();
    }
}
