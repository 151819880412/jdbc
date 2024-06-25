package com.atguigu.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author: Admin
 * @Create: 2024/5/30 - 下午4:04
 * @Version: v1.0
 * ClassName: JDBCQuick
 * Package: com.atguigu.base
 * Description: 描述
 */
public class JDBCQuick {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3. 获取执行SQL语句的对象
        Statement statement = connection.createStatement();
        // 4. 编写SQL语句
        String sql = "SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp";
        // 5. 执行
        ResultSet resultSet = statement.executeQuery(sql);
        // 6. 处理结果
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + " " + empName + " " + empSalary + " " + empAge);
        }
        // 7. 释放资源(先开后关原则)
        resultSet.close();
        statement.close();
        connection.close();

    }
}
