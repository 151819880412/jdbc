package com.atguigu.base.advanced.pojo;

/**
 * @Author: Admin
 * @Create: 2024/6/25 - 下午5:58
 * @Version: v1.0
 * ClassName: Employee
 * Package: com.atguigu.base.advanced.pojo
 * Description: 描述
 */
public class Employee {
    private int empId;
    private String empName;
    private double empSalary;
    private int empAge;

    public Employee() {
    }

    public Employee(int empId, String empName, double empSalary, int empAge) {
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.empAge = empAge;
    }

    public Employee(Integer o, String empName, int empSalary, int empAge) {
        this.empName = empName;
        this.empSalary = (double)empSalary;
        this.empAge = empAge;
    }

    public int getEmpId() {
        return this.empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return this.empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getEmpSalary() {
        return this.empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public int getEmpAge() {
        return this.empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    @Override
    public String toString() {
        return "Employee{empId=" + this.empId + ", empName='" + this.empName + "', empSalary=" + this.empSalary + ", empAge=" + this.empAge + "}";
    }
}
