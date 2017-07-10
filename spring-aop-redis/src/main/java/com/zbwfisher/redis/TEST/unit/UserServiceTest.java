package com.zbwfisher.redis.TEST.unit;


import com.zbwfisher.redis.TEST.entity.User;
import com.zbwfisher.redis.TEST.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ace on 2017/5/21.
 */
//@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
//@SpringBootTest(classes = CacheTest.class) // 指定我们SpringBoot工程的Application启动类
@Component
public class UserServiceTest {
    @Autowired
    private UserService userService;

    public void testGetUser() {
        userService.get("test");
    }

    public void testGetList() {
        userService.getLlist();
    }

    public void testGetSet() {
        userService.getSet();
        userService.getSet();
    }

    public void testGetMap() {
        userService.getMap();
    }


    public void testSave() {
        userService.save(new User("ace", 25, "ace"));
    }

    public void testByKeyGenerator() {
        userService.get(28);
        userService.get(28);
    }
}
