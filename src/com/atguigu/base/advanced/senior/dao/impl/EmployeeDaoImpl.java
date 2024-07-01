package com.atguigu.base.advanced.senior.dao.impl;
import com.atguigu.base.advanced.senior.dao.BaseDAO;
import com.atguigu.base.advanced.senior.dao.EmployeeDao;
import com.atguigu.base.advanced.senior.pojo.Employee;

import java.util.List;

/**
 * @Author: Admin
 * @Create: 2024/6/26 - 下午2:42
 * @Version: v1.0
 * @ClassName: EmployeeImpl
 * @Package: com.atguigu.base.advanced.senior.dao.impl
 * @Description: 描述
 */
public class EmployeeDaoImpl extends BaseDAO implements EmployeeDao {
    @Override
    public List<Employee> selectAll() {
        //  1. 注册驱动
        //  2. 获取连接
        //  3. 获取预编译sql语句
        //  4. 为占位符赋值,执行 SQL 语句,接受返回的结果
        //  5. 处理结果
        //  6. 释放资源
        String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp";
        try {
            return executeQuery(sql, Employee.class,null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee selectByEmpId(String empId) {
        String sql = "SELECT emp_id empId,emp_name empName,emp_salary empSalary,emp_age empAge FROM t_emp where emp_id = ?";
        try {
            return executeQueryBean(sql, Employee.class,empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Employee employee) {
        String sql = "INSERT INTO t_emp (emp_name , emp_salary, emp_age) VALUES (?,?,?)";
        try {
            return executeUpdate(sql, employee.getEmpName(), employee.getEmpSalary(), employee.getEmpAge());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Employee employee) {
        String sql = "UPDATE  t_emp SET emp_salary = ? WHERE emp_id = ?";
        try {
            return executeUpdate(sql, employee.getEmpSalary(), employee.getEmpId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int empId) {
        String sql = "delete from t_emp  where emp_id = ?";
        try {
            return executeUpdate(sql, empId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> insertReturnPrimaryKey(Employee employee) {
        return List.of();
    }

    @Override
    public List<Employee> insertMore(List<Employee> employees) {
        return List.of();
    }
}
