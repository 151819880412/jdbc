package com.atguigu.base.advanced.senior;
import com.atguigu.base.advanced.senior.util.JDBCUtil;
import com.atguigu.base.advanced.senior.util.JDBCUtilV2;
import org.junit.Test;
import java.sql.Connection;

/**
 * @Author: Admin
 * @Create: 2024/6/26 - 上午10:20
 * @Version: v1.0
 * ClassName: JDBCUtilTest
 * Package: com.atguigu.base.advanced.senior
 * Description: 描述
 */
public class JDBCUtilTest {
    @Test
    public void testGetConnection(){
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
        JDBCUtil.relese(connection);
    }

    @Test
    public void testGetConnectionV2(){
        /*
        获取的是三个不同的连接
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);*/

        // 获取的是三个相同的连接
        Connection connection1 = JDBCUtilV2.getConnection();
        Connection connection2 = JDBCUtilV2.getConnection();
        Connection connection3 = JDBCUtilV2.getConnection();
        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }

}
