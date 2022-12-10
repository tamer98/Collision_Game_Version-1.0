package com.example.hw1;


import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import com.google.android.material.imageview.ShapeableImageView;

public class GameManager {

    private int wrong = 0;
    private int life;
    static int liveLocation = 0;

    private ArrayList<Obstacle> obstacles;

    public GameManager(int life) {
        this.life = life;
        obstacles = DataManager.getObstacles();
    }


    public int getWrong() {
        return wrong;
    }

    public boolean isLose() {
        return life == wrong;
    }

    public int getLiveLocation() {
        return liveLocation;
    }


    public void movePlayerLocation(ShapeableImageView[] locations, int answer) {
        liveLocation = liveLocation + answer;
        if (liveLocation == 1) {
            locations[1].setVisibility(View.INVISIBLE);
            locations[2].setVisibility(View.VISIBLE);

        } else if (liveLocation == -1) {
            locations[1].setVisibility(View.INVISIBLE);
            locations[0].setVisibility(View.VISIBLE);

        } else if (liveLocation == 0) {
            locations[0].setVisibility(View.INVISIBLE);
            locations[2].setVisibility(View.INVISIBLE);
            locations[1].setVisibility(View.VISIBLE);
        }
    }


    public void moveObstacleLocation(ShapeableImageView[] img_obstacle) {
        int level;
        boolean flag = false;


        for (int i = obstacles.size() - 1; i >= 0; i--) {
            level = obstacles.get(i).getLevel();
            if (obstacles.get(i).isOn() == true && level > 1 && flag == false) {
                obstacles.get(i).setOn(false);
                img_obstacle[i].setVisibility(View.INVISIBLE);


                obstacles.get(i - 3).setOn(true);
                img_obstacle[i - 3].setVisibility(View.VISIBLE);


                break;
            }
        }
    }


    public void setRandomObstacle(ShapeableImageView[] img_obstacle) {
        int random = new Random().nextInt(3) + 9; // [0, 2] + 9 => [9, 11]

        obstacles.get(random).setOn(true);
        img_obstacle[random].setVisibility(View.VISIBLE);
    }


    public boolean checkAnswer(ShapeableImageView[] img_obstacle, ShapeableImageView[] locations, Vibrator v) {

        for (int i = 0; i < 3; i++) {
            if (obstacles.get(i).isOn() == true && img_obstacle[i].getVisibility() == View.VISIBLE
                    && locations[i].getVisibility() == View.VISIBLE) {
                wrong++;
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);

                }
                obstacles.get(i).setOn(false);
                img_obstacle[i].setVisibility(View.INVISIBLE);
                return true;
            }

        }
        return false;
    }

}