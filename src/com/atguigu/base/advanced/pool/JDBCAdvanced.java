package com.atguigu.base.advanced.pool;

import com.atguigu.base.advanced.pojo.Employee;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: Admin
 * @Create: 2024/6/25 - 下午6:00
 * @Version: v1.0
 * ClassName: JDBCAdvanced
 * Package: com.atguigu.base.advanced.pool
 * Description: 描述
 */
public class JDBCAdvanced {
    public JDBCAdvanced() {
    }

    @Test
    public void testORM() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        String sql = "select emp_id,emp_name,emp_salary,emp_age from t_emp where emp_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        if (resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println("" + empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
        }

        System.out.println(employee);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testORM2() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        String sql = "select emp_id,emp_name,emp_salary,emp_age from t_emp ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        Employee employee = null;
        List<Employee> employeeList = new ArrayList();

        while(resultSet.next()) {
            employee = new Employee();
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            employeeList.add(employee);
        }

        Iterator var12 = employeeList.iterator();

        while(var12.hasNext()) {
            Employee emp = (Employee)var12.next();
            System.out.println(emp);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testReturnPrimaryKey() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        String sql = "insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, 1);
        Employee employee = new Employee((Integer)null, "小明", 10000, 20);
        preparedStatement.setString(1, employee.getEmpName());
        preparedStatement.setDouble(2, employee.getEmpSalary());
        preparedStatement.setInt(3, employee.getEmpAge());
        int result = preparedStatement.executeUpdate();
        ResultSet resultSet = null;
        if (result > 0) {
            System.out.println("插入成功");
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int empId = resultSet.getInt(1);
                employee.setEmpId(empId);
            }

            System.out.println(employee);
        } else {
            System.out.println("插入失败");
        }

        preparedStatement.close();
        connection.close();
        if (resultSet != null) {
            resultSet.close();
        }

    }

    @Test
    public void testMoreInsert() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");
        String sql = "insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();

        for(int i = 0; i < 10000; ++i) {
            preparedStatement.setString(1, "小明批量" + i);
            preparedStatement.setDouble(2, (double)(1 + i));
            preparedStatement.setInt(3, 1 + i);
            preparedStatement.executeUpdate();
        }

        long end = System.currentTimeMillis();
        System.out.println("批量插入1000条数据,耗时:" + (end - start) + "毫秒");
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testBatchInsert() throws Exception {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?rewriteBatchedStatements=true", "root", "qwert123");
        String sql = "insert into t_emp(emp_name,emp_salary,emp_age) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();

        for(int i = 0; i < 10000; ++i) {
            preparedStatement.setString(1, "小明批量" + i);
            preparedStatement.setDouble(2, (double)(1 + i));
            preparedStatement.setInt(3, 1 + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("批量插入1000条数据,耗时:" + (end - start) + "毫秒");
        preparedStatement.close();
        connection.close();
    }
}
