package com.xk.task.data.util;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

//数据库工具类
public class DBUtil {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //读取配置文件初始化连接池参数
    static {
        Properties pro = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("c3p0.properties");
        try {
            pro.load(in);
            dataSource.setDriverClass(pro.getProperty("driver"));
            dataSource.setJdbcUrl(pro.getProperty("url"));
            dataSource.setUser(pro.getProperty("username"));
            dataSource.setPassword(pro.getProperty("password"));
            dataSource.setInitialPoolSize(Integer.parseInt(pro.getProperty("initSize")));
            dataSource.setMaxPoolSize(Integer.parseInt(pro.getProperty("maxSize")));
            dataSource.setMaxIdleTime(Integer.parseInt(pro.getProperty("maxIdel")));

        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }

    }

    //获得连接池中连接的方法
    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }

    //获得连接池对象的方法
    public static ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    //释放资源的方法
        public static void closeAll(AutoCloseable... autoCloseables) {
        for (AutoCloseable auto : autoCloseables
        ) {
            try {
                if (auto != null) auto.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



}

