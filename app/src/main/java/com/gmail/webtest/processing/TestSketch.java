package com.gmail.webtest.processing;

import processing.core.PApplet;

public class TestSketch extends PApplet {
    private int width, height;

    public TestSketch(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void settings(){
//        サイズは必ずしもviewのサイズとは一致しない
//        fullScreen();

        size(width, height);
    }

    @Override
    public void setup(){
        ellipse(0, 0, 50, 50);
        ellipse(width, height, 50, 50);
    }

    @Override
    public void draw(){
        if(mousePressed){
            ellipse(mouseX, mouseY, 50, 50);
        }
    }
}
