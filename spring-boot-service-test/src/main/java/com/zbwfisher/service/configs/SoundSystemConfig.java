package com.zbwfisher.service.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by zbw on 17/6/23.
 */
@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:cons-injec.xml") //导入xml配置项
public class SoundSystemConfig {

}