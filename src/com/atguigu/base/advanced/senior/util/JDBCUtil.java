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
 * @Version: v1.0
 * ClassName: JDBCUtil
 * Package: com.atguigu.base.advanced.senior.util
 * Description:
 *  1. 维护一个连接池对象
 *  2. 对外提供在连接池中获取连接的方法
 *  3. 对外提供在连接池中释放连接的方法
 *  注意:工具类仅对外提供共性的功能代码,所以方法都是静态方法
 */
public class JDBCUtil {
    //  创建一个连接池引用,因为要提供给当前项目的全局使用,所以创建为静态的
    static DataSource dataSource;
    //  在项目启动时创建连接池对象,赋值给 dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //  2. 对外提供在连接池中获取连接的方法
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  3. 对外提供在连接池中释放连接的方法
    public static void relese(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
