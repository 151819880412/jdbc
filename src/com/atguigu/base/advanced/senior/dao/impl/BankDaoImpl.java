package com.atguigu.base.advanced.senior.dao.impl;

import com.atguigu.base.advanced.senior.dao.BankDAO;
import com.atguigu.base.advanced.senior.dao.BaseDAO;

import java.sql.SQLException;

/**
 * @Author: Admin
 * @Create: 2024/7/1 - 上午11:43
 * @Version: v1.0
 * ClassName: BankDaoImpl
 * Package: com.atguigu.base.advanced.senior.dao.impl
 * Description: 描述
 */
public class BankDaoImpl  extends BaseDAO implements BankDAO {
    @Override
    public int addMoney(Integer id,Integer money){
        try {
            String sql = "UPDATE t_bank SET money = money + ? where id = ? ";
            return executeUpdate(sql,money,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subMoney(Integer id,Integer money){
        try {
            String sql = "update t_bank set money = money - ? where id = ? ";
            return executeUpdate(sql,money,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
