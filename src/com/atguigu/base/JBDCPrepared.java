package com.atguigu.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * @Author: Admin
 * @Create: 2024/6/3 - 下午3:12
 * @Version: v1.0
 * ClassName: JBDCPrepared
 * Package: com.atguigu.base
 * Description: 描述
 */
public class JBDCPrepared {
    public static void main(String[] args) throws Exception {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        // 3. 获取执行SQL语句的对象
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_name = ?");
        System.out.println(("请输入员工姓名："));
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        // 4. 为？占位符赋值
        preparedStatement.setString(1, name);
        // 5. 执行
        ResultSet resultSet = preparedStatement.executeQuery();
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
        preparedStatement.close();
        connection.close();
    }
}
