package com.example.hw1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    ExtendedFloatingActionButton player_FAB_right;
    ExtendedFloatingActionButton player_FAB_left;
    private ShapeableImageView[] game_IMG_locations;
    private ShapeableImageView[] game_IMG_hearts;
    private ShapeableImageView[] game_IMG_obstacle;



    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();
        initViews();
        gameManager = new GameManager(game_IMG_hearts.length);
        startTimer();
        startRandom();
        checkStatus();
    }


    private void initViews(){
        player_FAB_right.setOnClickListener(view -> {
            clicked(1);
        });
        player_FAB_left.setOnClickListener(view -> {
            clicked(-1);
        });
    }


    private void findViews() {
        player_FAB_right = findViewById(R.id.FAB_right);
        player_FAB_left = findViewById(R.id.FAB_left);
        game_IMG_hearts = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_heart1),
                findViewById(R.id.game_IMG_heart2),
                findViewById(R.id.game_IMG_heart3)
        };

        game_IMG_locations = new ShapeableImageView[]{
                findViewById(R.id.game_IMG_player_left), // 0
                findViewById(R.id.game_IMG_player_center), // 1
                findViewById(R.id.game_IMG_player_right) // 2
        };

        game_IMG_obstacle = new ShapeableImageView[]{
                findViewById(R.id.game_OB_21), // level 1
                findViewById(R.id.game_OB_22), // level 1
                findViewById(R.id.game_OB_23), // level 1
                findViewById(R.id.game_OB_31), // level 2
                findViewById(R.id.game_OB_32), // level 2
                findViewById(R.id.game_OB_33), // level 2
                findViewById(R.id.game_OB_41), // level 3
                findViewById(R.id.game_OB_42), // level 3
                findViewById(R.id.game_OB_43), // level 3
                findViewById(R.id.game_OB_51), // level 4
                findViewById(R.id.game_OB_52), // level 4
                findViewById(R.id.game_OB_53) // level 4
        };
        atPlayerStart();
        atObstacleStart();
    }


    private void clicked(int answer) {
        gameManager.movePlayerLocation(game_IMG_locations,answer);

    }


    public void atPlayerStart(){
        game_IMG_locations[0].setImageResource(R.drawable.catie);
        game_IMG_locations[1].setImageResource(R.drawable.catie);
        game_IMG_locations[2].setImageResource(R.drawable.catie);

        game_IMG_locations[0].setVisibility(View.INVISIBLE);
        game_IMG_locations[1].setVisibility(View.VISIBLE);
        game_IMG_locations[2].setVisibility(View.INVISIBLE);
    }

    public void atObstacleStart() {
        game_IMG_obstacle[0].setImageResource(R.drawable.dog);
        game_IMG_obstacle[1].setImageResource(R.drawable.dog);
        game_IMG_obstacle[2].setImageResource(R.drawable.dog);
        game_IMG_obstacle[3].setImageResource(R.drawable.dog);
        game_IMG_obstacle[4].setImageResource(R.drawable.dog);
        game_IMG_obstacle[5].setImageResource(R.drawable.dog);
        game_IMG_obstacle[6].setImageResource(R.drawable.dog);
        game_IMG_obstacle[7].setImageResource(R.drawable.dog);
        game_IMG_obstacle[8].setImageResource(R.drawable.dog);
        game_IMG_obstacle[9].setImageResource(R.drawable.dog);
        game_IMG_obstacle[10].setImageResource(R.drawable.dog);
        game_IMG_obstacle[11].setImageResource(R.drawable.dog);

        game_IMG_obstacle[0].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[1].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[2].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[3].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[4].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[5].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[6].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[7].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[8].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[9].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[10].setVisibility(View.INVISIBLE);
        game_IMG_obstacle[11].setVisibility(View.INVISIBLE);
    }


    private void refreshUI() {
        boolean boom = false;
        Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        boom=gameManager.checkAnswer(game_IMG_obstacle,game_IMG_locations, v);

        if (gameManager.isLose()) {
            finish();

        } else if (gameManager.getWrong()!= 0){
                game_IMG_hearts[game_IMG_hearts.length-gameManager.getWrong()].setVisibility(View.INVISIBLE);
        }

        if (boom==true)
            toast();
    }


    private void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() ->  gameManager.moveObstacleLocation(game_IMG_obstacle));
            }
        }, 1000, 1000);
    }

    private void startRandom() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() ->  gameManager.setRandomObstacle(game_IMG_obstacle));
            }
        }, 5000, 5000);
    }

    private void checkStatus() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() ->  refreshUI());
            }
        }, 2000, 2000);
    }


    private void toast() {
        Toast
                .makeText(this,"BOOM ! " ,Toast.LENGTH_LONG)
                .show();
    }
}

