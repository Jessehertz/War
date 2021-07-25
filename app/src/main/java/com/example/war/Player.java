package com.example.war;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Player extends AppCompatActivity {

    EditText player1, player2;
    ImageView start;
    String p1,p2;
    Animation fadeIn,fadeOut,slideLeft,slideRight;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        actionBar = getSupportActionBar();
        actionBar.hide();


        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        start = findViewById(R.id.start);

        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadeout);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(fadeOut);
                p1 = player1.getText().toString().trim();
                p2 = player2.getText().toString().trim();
                if (p1.length() == 0 || p2.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill in all the details", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), War.class);
                    intent.putExtra("p1", p1);
                    intent.putExtra("p2", p2);
                    startActivity(intent);
                    finish();

                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),Menu.class);
        startActivity(intent);
        finish();
    }
}