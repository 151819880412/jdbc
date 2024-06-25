# 核心API

## 1.注册驱动

    Class.forName("com.mysql.cj.jdbc.Driver");

## 2. Connection 建立连接

    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "qwert123");

## 3. Statement 创建语句

1. 会产生SQL注入攻击问题
    ```java
    // 3. 获取执行SQL语句的对象
    Statement statement = connection.createStatement();
    // 4. 编写SQL语句
    String sql = "SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp";
    // 5. 执行
    ResultSet resultSet = statement.executeQuery(sql);
    ```

## 4. PreparedStatement 创建语句

1. 不会产生SQL注入攻击问题
2. 性能提升: 预编译SQL语句，统一SQL语句多次执行的情况下，可以复用
    ```java
    // 3. 获取执行SQL语句的对象
    PreparedStatement preparedStatement = connection.prepareStatement("SELECT emp_id,emp_name,emp_salary,emp_age FROM t_emp WHERE emp_name = ?");
    System.out.println(("请输入员工姓名："));
    Scanner scanner = new Scanner(System.in);
    String name = scanner.nextLine();
    // 4. 为？占位符赋值
    preparedStatement.setString(1,name);
    // 5. 执行
    ResultSet resultSet = preparedStatement.executeQuery();
    ```

## 5. ResultSet 获取结果集

1. 执行查询语句所返回的结果集
    ```java
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
    ```