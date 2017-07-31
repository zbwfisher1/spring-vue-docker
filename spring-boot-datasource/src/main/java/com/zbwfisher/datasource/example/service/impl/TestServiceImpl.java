package com.zbwfisher.datasource.example.service.impl;


import com.github.nickvl.xspring.core.log.aop.annotation.LogDebug;
import com.github.nickvl.xspring.core.log.aop.annotation.LogException;
import com.github.nickvl.xspring.core.log.aop.annotation.LogInfo;
import com.zbwfisher.datasource.example.dao.ITestDAO;
import com.zbwfisher.datasource.example.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import javax.websocket.Endpoint;

/**
 * Created by zhong on 2016/9/5.
 */

@LogDebug
//@Endpoint
@Service("testService")
public class TestServiceImpl implements ITestService {

    @Autowired
    ITestDAO testDAO;


    @LogInfo
    @LogException(value = {@LogException.Exc(value = Exception.class, stacktrace = true)}, warn = {@LogException.Exc({IllegalArgumentException.class})})
    @Override
    public String test(String param) {

        String str = null;
        try {

            str.length();//将抛出异常
//        } catch (RuntimeException e){
//            throw new RuntimeException("运行时异常 测试 ");
        } catch (Exception e) {
//            throw new NullPointerException("12313131");
//            throw new Exception("asdasdas");
//            throw new RuntimeException("编号已经存在");
        }

        try {

            this.testDAO.testMaster();
            this.testDAO.testSlave1();
            this.testDAO.testSlave2();

            if (true) {
                throw new RuntimeException("编号已经存在");
//            boolean b = this.validateCodeBySectionType(s.getSectionType(),sectionCodeT);
//            if(b){ //编码重复
//                throw new RuntimeException("编号已经存在");
//            }
            } else {
                throw new NullPointerException("编号为空");
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
//


        return "测试异常信息";

    }


    @LogInfo
    @LogException(value = {@LogException.Exc(value = Exception.class, stacktrace = true)}, warn = {@LogException.Exc({IllegalArgumentException.class})})
    @Override
    public String test2(String param) {


        System.out.println();
        Long startTime = System.currentTimeMillis();
        this.testDAO.testQuery();
        this.testDAO.testQuery();
//        this.testDAO.testQuery2();

        System.out.println("花费时间 "+(System.currentTimeMillis()-startTime));

        return "测试异常信息";

    }


}
