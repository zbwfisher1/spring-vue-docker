package com.zbwfisher.async.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zbw on 17/7/30.
 */
@RestController
@Controller
public class Tester {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public String greeting() {


        Long timeLine = System.currentTimeMillis();
        User user1 = new User();
        User user2 = new User();
        int num = 0;
//        for (int i = 0; i < 2000; i++) {
        user1 = userService.addUser(new User(34, "李一 _" + (num++)));
        user2 = userService.addUser(new User(35, "李二 _" + (num++)));
//        }
        logger.info("异步任务已执行" + (System.currentTimeMillis() - timeLine));
        logger.info("执行结果  任务1：{}  任务2：{}", user1.getName(), user2.getName());
        System.out.println("异步任务已执行" + (System.currentTimeMillis() - timeLine));
        return "hello";
    }


}
