package com.zbwfisher.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class SpringAopAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopAsyncApplication.class, args);
	}
}
