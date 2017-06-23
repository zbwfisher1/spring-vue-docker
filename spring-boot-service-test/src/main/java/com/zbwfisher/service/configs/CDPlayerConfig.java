package com.zbwfisher.service.configs;

import com.zbwfisher.service.configTest.CDPlayer;
import com.zbwfisher.service.configTest.CD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by zbw on 17/6/23.
 *
 *
 */

//todo @Configuration注解表示定义一个配置类，
//todo 这里使用注解@Bean就好比如xml配置时的<bean>元素，如：
//todo <bean id="cdPlayer" class="com.jiaobuchong.soundsystem.CDPlayer">
//todo <property name="cd" ref="CD" />
//todo </bean>
@Configuration
@Import(CDConfig.class)  //导入CDConfig的配置
public class CDPlayerConfig {
    @Bean(name = "cDPlayer")
    public CDPlayer cdPlayer(CD cd) {
    /*这里会注入CD类型的bean
     这里注入的这个bean是CDConfig.class中的CD类型的那个bean*/
        return new CDPlayer(cd);
    }

}