package com.example.hw1;

public class Obstacle {
    private int imageRes;
    private int level;
    private boolean isOn;


    public Obstacle() {
    }


    public int getLevel() {
        return level;
    }

    public Obstacle setLevel(int level) {
        this.level = level;
        return this;
    }

    public boolean isOn() {
        return isOn;
    }

    public Obstacle setOn(boolean on) {
        isOn = on;
        return this;
    }
}
