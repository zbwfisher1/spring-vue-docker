package com.zbwfisher.redis;

import com.zbwfisher.redis.common.config.RedisConfig;
import com.zbwfisher.redis.common.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@EnableAspectJAutoProxy
//@EnableAutoConfiguration
//@ImportAutoConfiguration
//@Import({RedisConfig.class, WebConfig.class})
//@ComponentScan({"com.zbwfisher.redis"})
public class SpringAopRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopRedisApplication.class, args);
	}
}
