package com.atguigu.base.advanced.senior;
import com.atguigu.base.advanced.senior.util.JDBCUtil;
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
}
