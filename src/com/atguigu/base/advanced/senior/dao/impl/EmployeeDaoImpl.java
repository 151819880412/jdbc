package com.atguigu.base.advanced.senior.dao.impl;

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
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> selectAll() {
        //  1. 注册驱动
        //  2. 获取连接
        //  3. 获取预编译sql语句
        //  4. 为占位符赋值,执行 SQL 语句,接受返回的结果
        //  5. 处理结果
        //  6. 释放资源
        return List.of();
    }

    @Override
    public List<Employee> selectByEmpId(String empId) {
        return List.of();
    }

    @Override
    public List<Employee> insert(Employee employee) {
        return List.of();
    }

    @Override
    public List<Employee> update(Employee employee) {
        return List.of();
    }

    @Override
    public List<Employee> delete(String empId) {
        return List.of();
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
