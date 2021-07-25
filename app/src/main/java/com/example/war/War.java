package com.example.war;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class War extends AppCompatActivity {
    ActionBar actionBar;
    TextView leftScore, rightScore, restart, p1,p2;
    ImageView player1card, player2card, start, player1cardView, player2cardView;
    int left = 0, right = 0, l, r, player1Turn= 0, player2Turn =0;
    Animation fadeIn,fadeOut,slideOutUp,slideOutDown,slideRight,slideup,slidedown,bounce,blink,zoomOut;
    Random rand;
    Handler h,h1,h2,h4,h3,h5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_war);

        actionBar = getSupportActionBar();
        actionBar.hide();

        start = findViewById(R.id.start);
        player1card = findViewById(R.id.player1card);
        player2card = findViewById(R.id.player2card);
        player1cardView = findViewById(R.id.player1cardView);
        player2cardView = findViewById(R.id.player2cardView);
        leftScore = findViewById(R.id.leftScore);
        rightScore = findViewById(R.id.rightScore);
        restart = findViewById(R.id.restart);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);

        p1.setText(getIntent().getStringExtra("p1"));
        p2.setText(getIntent().getStringExtra("p2"));

        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        slideOutUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_up);
        slideOutDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_down);
        slideRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        slideup = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        slidedown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        zoomOut  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomin);

        player1card.startAnimation(blink);

        player1card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player1Turn ==  player2Turn){
                    v.startAnimation(slideOutUp);

                    player1Turn++;
                    l = rand.nextInt(12) + 1;
                    String PACKAGE_NAME = getApplicationContext().getPackageName();
                    int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+l , null, null);

                    h1 = new Handler();
                    h1.postDelayed(new Runnable() {
                        public void run() {
                            player1cardView.setVisibility(View.VISIBLE);
                            player1cardView.startAnimation(zoomOut);
                            player1cardView.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
                        }
                    }, 400);

                    h3 = new Handler();
                    h3.postDelayed(new Runnable() {
                        public void run() {
                            player2card.startAnimation(blink);
                        }
                    }, 700);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Wait your turn!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        player2card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player2Turn  < player1Turn){
                    v.startAnimation(slideOutDown);

                    player2Turn++;
                    r = rand.nextInt(12) + 1;
                    String PACKAGE_NAME = getApplicationContext().getPackageName();
                    int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+r , null, null);

                    h2 = new Handler();
                    h2.postDelayed(new Runnable() {
                        public void run() {
                            player2cardView.setVisibility(View.VISIBLE);
                            player2cardView.startAnimation(zoomOut);
                            player2cardView.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
                        }
                    }, 400);

                    h4 = new Handler();
                    h4.postDelayed(new Runnable() {
                        public void run() {
                            player1card.startAnimation(blink);
                        }
                    }, 700);

                    score();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wait your turn!", Toast.LENGTH_SHORT).show();
                }
            }

        });


        rand = new Random();
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(fadeOut);

                /*String PACKAGE_NAME = getApplicationContext().getPackageName();

                int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+0 , null, null);
                player1cardView.startAnimation(slideOutUp);
                player1card.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));

                player2card.startAnimation(slideRight);
                int imgId2 = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+0 , null, null);
                player2card.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId2));

                left = 0;
                leftScore.setText(left+"");

                right = 0;
                rightScore.setText(right+"");*/

                Intent intent = new Intent(getApplicationContext(), Player.class);
                startActivity(intent);
                finish();
            }
        });

        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(fadeOut);

                int L = rand.nextInt(12) + 1;
                int R = rand.nextInt(12) + 1;

                String PACKAGE_NAME = getApplicationContext().getPackageName();

                int imgId = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+L , null, null);
                player1card.startAnimation(slideOutUp);
                player1card.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));

                player2card.startAnimation(slideRight);
                int imgId2 = getResources().getIdentifier(PACKAGE_NAME+":drawable/card"+R , null, null);
                player2card.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId2));




            }
        });*/


    }

    public void score(){
        if(l > r){
            left++;
            h5 = new Handler();
            h5.postDelayed(new Runnable() {
                public void run() {
                    leftScore.setText(left+"");
                }
            }, 1200);


        }else if (r > l)
        {
            right++;
            h5 = new Handler();
            h5.postDelayed(new Runnable() {
                public void run() {
                    rightScore.setText(right+"");
                }
            }, 1200);


        }
        else{
            Toast.makeText(getApplicationContext(), "WAR", Toast.LENGTH_SHORT).show();

        }

        if (left == 7){
            new AlertDialog.Builder(War.this)
                    .setMessage(p1.getText()+" has won!")
                    .show()
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {

                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //your logic
                            Intent intent = new Intent(getApplicationContext(), Player.class);
                            startActivity(intent);
                        }
                    });

            h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Player.class);
                    startActivity(intent);
                }
            }, 3500);


        }
        if (right == 7){
            new AlertDialog.Builder(War.this)
                    .setMessage(p2.getText()+" has won!")
                    .show().setOnCancelListener(new DialogInterface.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    //your logic
                    Intent intent = new Intent(getApplicationContext(), Player.class);
                    startActivity(intent);
                }
            });

            h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Player.class);
                    startActivity(intent);
                }
            }, 4000);

        }

    }



    @Override
    public void onBackPressed() {
        // Simply Do noting!
    }
}