package com.zbwfisher.service.configTest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zbw on 17/6/23.
 */
public class CDPlayer implements CD {
    private CD cd;

    @Autowired//构造函数注入
    public CDPlayer(CD cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}