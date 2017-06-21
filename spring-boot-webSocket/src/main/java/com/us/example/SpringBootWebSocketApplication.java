package com.us.example;

/**
 * Created by yangyibo on 16/12/29.
 */
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@ComponentScan(basePackages ="com.us.example")
@SpringBootApplication
@EnableScheduling
public class SpringBootWebSocketApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = run(SpringBootWebSocketApplication.class, args);
    }
}