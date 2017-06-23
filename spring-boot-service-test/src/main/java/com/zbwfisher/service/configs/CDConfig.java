package com.zbwfisher.service.configs;

import com.zbwfisher.service.configTest.Peppers;
import com.zbwfisher.service.configTest.CD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zbw on 17/6/23.
 */

//todo @Configuration注解表示定义一个配置类，
//todo 这里使用注解@Bean就好比如xml配置时的<bean>元素，如：
//todo <bean id="cd" class="com.jiaobuchong.soundsystem.CD">
//todo </bean>

@Configuration
public class CDConfig {
    @Bean   // 将Peppers注册为 SpringContext中的bean
    public CD cd() {
        return new Peppers();  // CompactDisc类型的
    }
}