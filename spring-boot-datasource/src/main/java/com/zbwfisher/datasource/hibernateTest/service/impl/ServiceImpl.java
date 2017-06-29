package com.zbwfisher.datasource.hibernateTest.service.impl;


import com.sun.tools.javac.util.List;
import com.zbwfisher.datasource.hibernateTest.models.User;
import com.zbwfisher.datasource.hibernateTest.models.UserDao;
import com.zbwfisher.datasource.hibernateTest.service.HibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhong on 2016/9/5.
 */
@Service
public class ServiceImpl implements HibernateService {

    @Autowired
    UserDao userDao;

    @Override
    public void test() {
        List<User> list= (List<User>) userDao.getAll();
        userDao.create(new User());

//        System.out.println(list.toString());
    }
}
