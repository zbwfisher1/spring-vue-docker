package com.zbwfisher.service.configTest;

/**
 * Created by zbw on 17/6/23.
 */
public class Peppers implements CD {
    private String title = "Pepper's Lonely Hearts Club Band";
    private String artist = "The Beatles";
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }
}