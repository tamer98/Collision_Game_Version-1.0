package com.example.hw1;


import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class DataManager {

     static int obstacle_img = R.drawable.dog;
     final static int oNum = 12;
     static int level=1;

    public static ArrayList<Obstacle> getObstacles(){

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        for (int i = 0; i < oNum; i++) {
            Obstacle o = new Obstacle()
                    .setLevel(level)
                    .setOn(false);

                if((i+1) % 3 == 0) {
                    level++;
                }

            obstacles.add(o);
        }
        return obstacles;
    }


}
