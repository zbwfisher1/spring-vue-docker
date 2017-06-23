package com.zbwfisher.datasource;

import com.zbwfisher.datasource.druid.DynamicDataSource.DynamicDataSourceRegister;
import com.zbwfisher.datasource.druid.DynamicDataSource.MProxyTransactionManagementConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@EnableAutoConfiguration
@ImportAutoConfiguration
@Import({DynamicDataSourceRegister.class, MProxyTransactionManagementConfiguration.class})
public class SpringBootDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatasourceApplication.class, args);
	}
}
