package com.zbwfisher.service;


import com.zbwfisher.service.configs.SoundSystemConfig;
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
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest2 {
    @Autowired
    @Qualifier("cDPlayer")
    private CD cd;

    @Autowired
    @Qualifier("compactDisc")
    private CD cd1;

    @Test
    public void cdShouldNotBeNull() {
//        assertNotNull(cd);
//        cd.play();

        System.out.println();
        System.out.println(" ___________________________  ");
        cd.play();
        System.out.println(" ___________________________  ");
        System.out.println();
    }

    @Test
    public void testBlankDisc() {
//        cd1.play();


        System.out.println();
        System.out.println(" ___________________________  ");
        cd1.play();
        System.out.println(" ___________________________  ");
        System.out.println();
    }
}