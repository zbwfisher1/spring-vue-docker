package com.zbwfisher.datasource.example.controller;

import com.github.nickvl.xspring.core.log.aop.annotation.LogException;
import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import com.zbwfisher.datasource.example.service.ITestService;
import com.zbwfisher.datasource.hibernateTest.service.HibernateService;
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


    @LogInfo
    @LogException(value = {@LogException.Exc(value = Exception.class, stacktrace = true)}, warn = {@LogException.Exc({IllegalArgumentException.class})})
    @RequestMapping("/test")
    public String greeting() {
        try {
            testService.test("testParam");
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return "hello";
    }
}
