package com.example.jimmy.cornalarmclock.model;

/**
 * Created by Jimmy on 16/7/22.
 */
public class Title {

    public int leftView = 0;
    public int rightView = 0;
    public String title = "";

    public Title() {
        this.leftView = 0;
        this.rightView = 0;
        this.title = "";
    }

    public Title(String title, int left, int right) {
        this.title = title;
        this.leftView = left;
        this.rightView = right;
    }

}
