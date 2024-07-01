package com.atguigu.base.advanced.senior.dao;

/**
 * @Author: Admin
 * @Create: 2024/7/1 - 上午11:42
 * @Version: v1.0
 * ClassName: BankDAO
 * Package: com.atguigu.base.advanced.senior.dao
 * Description: 描述
 */
public interface BankDAO {
    public int addMoney(Integer id, Integer money);
    public int subMoney(Integer id, Integer money);
}
