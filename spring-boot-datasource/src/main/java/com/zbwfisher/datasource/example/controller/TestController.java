package com.zbwfisher.datasource.example.controller;

import com.github.nickvl.xspring.core.log.aop.annotation.LogException;
import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import com.zbwfisher.datasource.example.service.ITestService;
import com.zbwfisher.datasource.example.testservice.User;
import com.zbwfisher.datasource.example.testservice.UserService;
import com.zbwfisher.datasource.hibernateTest.service.HibernateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2016/11/10.
 */

@RestController
@Controller
public class TestController {
    @Autowired
    private ITestService testService;
    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @LogInfo
    @LogException(value = {@LogException.Exc(value = Exception.class, stacktrace = true)}, warn = {@LogException.Exc({IllegalArgumentException.class})})
    @RequestMapping("/test")
    public String greeting() {
        try {
            testService.test("testParam");
        } catch (Exception e) {
//            e.printStackTrace();
        }


//        try {
//            testService.test2("testParam");
//        } catch (Exception e) {
////            e.printStackTrace();
//        }


//        Long timeLine = System.currentTimeMillis();
//        User user1 = new User();
//        User user2 = new User();
//        int num = 0;
////        for (int i = 0; i < 2000; i++) {
//            user1 = userService.addUser(new User(34, "李一 _" + (num++)));
//            user2 = userService.addUser(new User(35, "李二 _" + (num++)));
////        }
//        logger.info("异步任务已执行" + (System.currentTimeMillis() - timeLine));
//        logger.info("执行结果  任务1：{}  任务2：{}", user1.getName(), user2.getName());
//        System.out.println("异步任务已执行" + (System.currentTimeMillis() - timeLine));


        return "hello";
    }


}
