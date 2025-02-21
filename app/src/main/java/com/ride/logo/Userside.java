package com.ride.logo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Userside extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordText, signUpText;
    private ImageView backArrow;
    private UserDatabaseHelper dbHelper; // Database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userside);

        // Initialize views
        emailEditText = findViewById(R.id.email_username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login12);
        forgotPasswordText = findViewById(R.id.forgot_password);
        signUpText = findViewById(R.id.NewRegistration);
        backArrow = findViewById(R.id.Back_arrow);

        dbHelper = new UserDatabaseHelper(this); // Initialize database helper

        // Back arrow click listener
        backArrow.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(Userside.this, R.anim.button_click);
            v.startAnimation(animation);
            Intent intent = new Intent(Userside.this, ButtonActivity.class);
            startActivity(intent);
            finish();
        });

        // Login button click listener
        loginButton.setOnClickListener(v -> {
            String emailOrName = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateUser(emailOrName, password)) {
                if (dbHelper.validateUser(emailOrName, password)) {
                    Toast.makeText(Userside.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Userside.this, Userhomepage1.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Userside.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Forgot password click listener
        forgotPasswordText.setOnClickListener(v ->
                Toast.makeText(Userside.this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show()
        );

        // Sign up click listener
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(Userside.this, UserRegistration.class);
            startActivity(intent);
        });
    }

    // Validate user inputs
    private boolean validateUser(String emailOrName, String password) {
        if (TextUtils.isEmpty(emailOrName)) {
            emailEditText.setError("Please enter your email or username");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter your password");
            return false;
        }
        return true;
    }
}
