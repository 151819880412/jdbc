package com.atguigu.base;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: Admin
 * @Create: 2024/6/13 - 上午9:32
 * @Version: v1.0
 * ClassName: JDBCOperation
 * Package: com.atguigu.base
 * Description: 描述
 */
public class JDBCOperation {
    @Test
    // 单行单列查询
    public void testQuerySingleRowAndColumn() throws Exception {
        // 1.注册驱动(可以省略)
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) as count FROM t_emp");
        // 4.执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        // 5.处理结果
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getInt("count"));
        }
        // 6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    // 单行多列查询
    public void testQuerySingleRow() throws Exception {
        // 1.注册驱动(可以省略)
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_id = ?");
        // 4.为占位符赋值
        preparedStatement.setInt(1, 1);
        // 5.执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt("emp_id"));
            System.out.println(resultSet.getString("emp_name"));
            System.out.println(resultSet.getString("emp_salary"));
            System.out.println(resultSet.getString("emp_age"));
        }
        // 6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    // 多行多列查询
    public void testQueryMultiRow() throws Exception {
        // 1.注册驱动(可以省略)
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_age > ?");
        // 4.为占位符赋值
        preparedStatement.setInt(1, 25);
        // 5.执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        // 6.释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    // 新增
    public void testInsert() throws Exception {
        // 1.注册驱动(可以省略)
        // 2.获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3.预编译SQL语句得到PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO t_emp(emp_name,emp_salary,emp_age) VALUES (?,?,?)");
        // 4.为占位符赋值
        preparedStatement.setString(1, "张三");
        preparedStatement.setDouble(2, 5000);
        preparedStatement.setInt(3, 25);
        int count = preparedStatement.executeUpdate();
        System.out.println(count > 0 ? "成功" : "失败");
        // 释放资源
        preparedStatement.close();
        connection.close();

    }

    @Test
    // 修改
    public void testUpdate() throws Exception {
        // 1.注册驱动(可以省略)
        // 2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3.创建Statement对象
        PreparedStatement preparedStatement = connection.prepareStatement("update t_emp set emp_salary = ? where emp_id = ?");
        // 4.为占位符赋值，索引从1开始，编写SQL语句并执行，获取结果
        preparedStatement.setDouble(1,888.88);
        preparedStatement.setDouble(2,1);
        int result = preparedStatement.executeUpdate();
        // 5.处理结果
        if(result>0){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败");
        }
        // 6.释放资源(先开后关原则)
        preparedStatement.close();
        connection.close();
    }

    @Test
    // 删除
    public void testDelete() throws Exception {
        // 1.注册驱动(可以省略)
        //2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        //3.创建Statement对象
        PreparedStatement preparedStatement = connection.prepareStatement("delete from t_emp where emp_id = ?");
        //4.为占位符赋值，索引从1开始，编写SQL语句并执行，获取结果
        preparedStatement.setInt(1,8);
        int result = preparedStatement.executeUpdate();
        //5.处理结果
        if(result>0){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }
        //6.释放资源(先开后关原则)
        preparedStatement.close();
        connection.close();
    }
}
