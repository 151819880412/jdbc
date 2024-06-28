package com.atguigu.base.advanced.senior;

import com.atguigu.base.advanced.senior.dao.EmployeeDao;
import com.atguigu.base.advanced.senior.dao.impl.EmployeeDaoImpl;
import com.atguigu.base.advanced.senior.pojo.Employee;
import com.atguigu.base.advanced.senior.util.JDBCUtil;
import com.atguigu.base.advanced.senior.util.JDBCUtilV2;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

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
    public void testGetConnection() {
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
        JDBCUtil.relese(connection);
    }

    @Test
    public void testGetConnectionV2() {
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

    @Test
    public void testEmployeeDAO() {
        //  1. 创建 DAO 实现类对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        //  2. 调用方法
        List<Employee> employeeList = employeeDao.selectAll();

        //  3. 处理结果
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    @Test
    public void selectById() {
        //  1. 创建 DAO 实现类对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        //  2. 调用方法
        Employee employee = employeeDao.selectByEmpId("1");

        //  3. 处理结果
        System.out.println(employee);

    }

    @Test
    public void insert() {
        //  1. 创建 DAO 实现类对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        Employee employee = new Employee(null, "张三", 3000, 20);

        //  2. 调用方法
        int insert = employeeDao.insert(employee);

        //  3. 处理结果
        System.out.println(insert);
    }

    @Test
    public void update() {
        //  1. 创建 DAO 实现类对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        Employee employee = new Employee(1, "张三", 3000, 20);

        //  2. 调用方法
        int insert = employeeDao.update(employee);

        //  3. 处理结果
        System.out.println(insert);
    }

    @Test
    public void delete() {
        //  1. 创建 DAO 实现类对象
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        //  2. 调用方法
        int insert = employeeDao.delete(62018);

        //  3. 处理结果
        System.out.println(insert);
    }

}
