package com.zbwfisher.datasource.example.controller;

import com.zbwfisher.datasource.example.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhong on 2016/11/10.
 */

@RestController
@Controller
public class TestController {
    @Autowired
    private ITestService testService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public String greeting() {
        testService.test();
        return "hello";
    }
}
