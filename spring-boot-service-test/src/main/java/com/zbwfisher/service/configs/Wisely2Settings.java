package com.zbwfisher.service.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zbw on 17/6/22.
 */
@Configuration
@ConfigurationProperties(prefix = "wisely2")
public class Wisely2Settings {
    private String name;
    private String gender;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}