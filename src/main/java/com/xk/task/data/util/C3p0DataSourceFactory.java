package com.xk.task.data.util;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class C3p0DataSourceFactory extends UnpooledDataSourceFactory {

    /**
     * 二、继承UnpooledDataSourceFactory的类
     *  Mybatis 没有帮开发者实现 c3p0 数据库连接池，故需要使用者自己实现 c3p0 来加载数据连接池。其实很简单的，
     *  只要继承 UnpooledDataSourceFactory 并把 dataSource 实现。我们的 mybatis 就实现了 c3p0 数据库连接池。
     */
    public C3p0DataSourceFactory(){
        this.dataSource=new ComboPooledDataSource();
    }
}
