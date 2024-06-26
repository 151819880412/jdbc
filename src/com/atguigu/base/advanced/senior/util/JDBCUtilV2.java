package com.atguigu.base.advanced.senior.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: Admin
 * @Create: 2024/6/25 - 下午6:48
 * @Version: v2.0
 * ClassName: JDBCUtil
 * Package: com.atguigu.base.advanced.senior.util
 * Description:
 *  1. 维护一个连接池对象,还维护了一个线程绑定变量的 ThreadLocal 对象
 *  2. 对外提供在 ThreadLocal 中获取连接的方法
 *  3. 对外提供在 ThreadLocal 中释放连接的方法,将要回收的连接从 ThreadLocal 中移除
 *  注意:工具类仅对外提供共性的功能代码,所以方法都是静态方法
 *  注意:使用 ThreadLocal 就是为了一个线程在多次数据库操作过程中,使用的都是同一个连接
 */
public class JDBCUtilV2 {
    //  创建一个连接池引用,因为要提供给当前项目的全局使用,所以创建为静态的
    private static DataSource dataSource;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    //  在项目启动时创建连接池对象,赋值给 dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtilV2.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //  2. 对外提供在连接池中获取连接的方法
    public static Connection getConnection() {
        try {
            //  从 threadLocal 中获取 Connection 连接
            Connection connection = threadLocal.get();
            // 第一次获取连接为 null,说明是第一次获取连接,需要创建连接
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  3. 对外提供在连接池中释放连接的方法
    public static void relese() {
        Connection connection = threadLocal.get();
        try {
            if (connection != null) {
                // 从 threadLocal 中移除已经存在连接
                threadLocal.remove();
                // 将 connection 归还给连接池
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
