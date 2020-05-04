package com.peppers.service;

import com.peppers.dao.UserDao;

/**
 * @ClassName UserServiceImpl
 * @Author peppers
 * @Date 2020/5/4
 * @Description
 **/
public class UserServiceImpl implements UserService{

    UserDao dao;
    public void find() {
        System.out.println("service");
        dao.query();
    }
}
