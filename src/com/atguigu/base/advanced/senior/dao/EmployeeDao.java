package com.atguigu.base.advanced.senior.dao;

import com.atguigu.base.advanced.senior.pojo.Employee;

import java.util.List;

/**
 * @Author: Admin
 * @Create: 2024/6/26 - 上午11:16
 * @Version: v1.0
 * ClassName: EmployeeDao
 * Package: com.atguigu.base.advanced.senior.dao
 * Description: 这个类对应的是 t_emp 这个表的增删改查操作
 */
public interface EmployeeDao {
    /*
    * 查询所有的操作
    * @return 表中所有的数据
    * */
    List<Employee> selectAll();

    /*
    * 根据empId查询操作
    * @param empId
    * @return
    * */
    Employee selectByEmpId(String empId);

    /*
    * 添加操作
    * @param employee
    * @return 受影响的行数
    * */
    int insert(Employee employee);

    /*
    * 修改操作
    * @param employee
    * @return 受影响的行数
    * */
    int update(Employee employee);

    /*
    * 删除操作
    * @param empId
    * @return 受影响的行数
    * */
    int delete(int empId);

    /*
    * 添加对象--主键回显
    * @param employee
    * @return 添加的对象
    * */
    List<Employee> insertReturnPrimaryKey(Employee employee);

    /*
    * 批量插入
    * @param employees
    * @return
    * */
    List<Employee> insertMore(List<Employee> employees);

}
