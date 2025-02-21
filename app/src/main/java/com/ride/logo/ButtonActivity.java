package com.ride.logo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personchoose); // Ensure this layout contains the buttons

        Button driverButton = findViewById(R.id.driverbutton);
        Button userButton = findViewById(R.id.userbutton);

        driverButton.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(ButtonActivity.this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(ButtonActivity.this, Driverside.class);
            startActivity(intent);
        });

        userButton.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(ButtonActivity.this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(ButtonActivity.this, Userside.class);
            startActivity(intent);
        });
    }
}