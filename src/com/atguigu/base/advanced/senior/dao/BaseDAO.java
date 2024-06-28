package com.atguigu.base.advanced.senior.dao;
import com.atguigu.base.advanced.senior.util.JDBCUtilV2;
import com.sun.jdi.Value;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Admin
 * @Create: 2024/6/26 - 下午2:48
 * @Version: v1.0
 * ClassName: BaseDao
 * Package: com.atguigu.base.advanced.senior.dao
 * Description: 将公共的数据库操作封装
 */
public class BaseDAO {
    /**
     * @param sql    sa
     * @param params sa
     * @return int
     */
    public int executeUpdate(String sql, Object... params) throws Exception {
        //  1.注册驱动(可以省略)
        //  2.通过 JDBCUtilv2 获取连接
        Connection connection = JDBCUtilV2.getConnection();
        //  3.获取执行SQL语句的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //  4.为占位符赋值,执行 SQL 接受返回结果
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //  占位符是从1开始的,参数的数组是从0开始的
                preparedStatement.setObject(i+1, params[i]);
            }
        }
        //  5.处理结果
        int row = preparedStatement.executeUpdate();
        //  6.释放资源
        JDBCUtilV2.relese();
        preparedStatement.close();
        return row;
    }

    /**
    * 通用的查询:多行多列,单行多列,单行单列
    *       多行多列 List<Employee>
    *       单行多列 Employee
    *       单行单列 封装的是一个结果 Double Integer ...
    *   封装过程:
    *       1. 返回的类型:泛型:类型不确定,调用者知道,调用时,将此次的结果类型告知 BaseDAO 就可以了
    *       2. 返回的结果:List 可以
    *       3. 结果的封装:反射,要求调用者告知 BaseDAO 要封装对象的类的类型
    * */
    public <T> List<T> executeQuery(String sql, Class<T> clazz, Object... params) throws Exception {
        //  1. 注册驱动(可以省略)
        //  2. 通过 JDBCUtilv2 获取连接
        Connection connection = JDBCUtilV2.getConnection();
        //  3. 预编译 SQL 语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //  4. 设置占位符的值
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //  占位符是从1开始的,参数的数组是从0开始的
                preparedStatement.setObject(i+1, params[i]);
            }
        }
        //  5. 执行 SQL 并接受返回的结果
        ResultSet resultSet = preparedStatement.executeQuery();

        //  6. 获取结果集合中的元数据对象
        //      包含了:列的个数,列的名称,列的别名,列的类型,列的注释
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<T> list = new ArrayList<T>();

        //  7. 遍历结果集合,封装成对象,放入集合中
        while (resultSet.next()) {
            //  循环一次,代表有一行数据,通过反射创建一个对象
            T t = clazz.newInstance();
            // 循环当前行的列,循环几次就有几个列
            for (int i = 1; i < columnCount; i++) {
                // 通过下标获取列的值
                Object value = resultSet.getObject(i);
                //  获取到的列的 value 值,这个值就是 t 这个对象中的某一个属性
                //  获取当前拿到的列的名字 = 对象的属性名
                String filedName = metaData.getColumnLabel(i);
                //  通过类对象和 filedName 获取要封装的对象的属性
                Field field = clazz.getDeclaredField(filedName);
                //  突破封装的 private
                field.setAccessible(true);
                field.set(t, value);
            }
            list.add(t);
        }
        //  9. 释放资源
        resultSet.close();
        preparedStatement.close();
        JDBCUtilV2.relese();

        return list;
    }
    
   /**
   *  通用查询:在上面查询的集合结果中获取第一个结果,简化了获取单行单列的获取,多行多列的获取
   * */
   public <T> T executeQueryBean(String sql, Class<T> clazz, Object... params) throws Exception {
       List<T> list = this.executeQuery(sql, clazz, params);
       if(list == null || list.size() == 0){
           return null;
       }
       return list.get(0);
   }
}
