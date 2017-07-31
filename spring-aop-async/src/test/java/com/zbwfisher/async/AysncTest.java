package com.zbwfisher.async;


import com.zbwfisher.async.core.AsyncFutureCallback;
import com.zbwfisher.async.core.AsyncTaskFuture;
import com.zbwfisher.async.template.AsyncTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/config/spring.xml"})
public class AysncTest {

    private final static Logger logger = LoggerFactory.getLogger(AysncTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void testAsyncAnnotation() {
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
        System.out.println();
    }


    @Test
    public void testAsyncTemplate() {

        AsyncTemplate.execute(
                new AsyncTaskFuture<User>() {

                    @Override
                    public User doAsync(Map<String, Object> dataMap) {

                        return teacherService.addTeacher(new User(12, "李三"));
                    }

                }, new AsyncFutureCallback<User>() {

                    @Override
                    public void onSuccess(User user) {
                        logger.info("添加用户成功：{}", user.getName());


                    }

                    @Override
                    public void onFailure(Throwable t) {
                        logger.info("添加用户失败：{}", t);
                    }

                }

        );
        logger.info("调用结束");
    }

}
