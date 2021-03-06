package com.zbwfisher.service;


import com.zbwfisher.service.configs.CDPlayerConfig;
import com.zbwfisher.service.configTest.CD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zbw on 17/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest1 {
    @Autowired
    @Qualifier("cDPlayer")
    private CD cd;

    @Test
    public void cdShouldNotBeNull() {
//        assertNotNull(cd);

        System.out.println();
        System.out.println(" ___________________________  ");
        cd.play();
        System.out.println(" ___________________________  ");
        System.out.println();

    }

}