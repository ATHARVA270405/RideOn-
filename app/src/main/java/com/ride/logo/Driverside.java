package com.ride.logo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Driverside extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView signUpText;
    private ImageView backArrow;
    private DatabaseHelper databaseHelper; // Database instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driverside);

        // Initialize views
        emailEditText = findViewById(R.id.email_username1);
        passwordEditText = findViewById(R.id.password1);
        loginButton = findViewById(R.id.login_button1);
        signUpText = findViewById(R.id.NewRegistration1);
        backArrow = findViewById(R.id.Back_arrow1);

        databaseHelper = new DatabaseHelper(this); // Initialize database helper

        // Back button listener
        backArrow.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            startActivity(new Intent(this, ButtonActivity.class));
            finish();
        });

        // Login button listener
        loginButton.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.button_click));
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                if (authenticateUser(email, password)) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, driver_home.class));
                    finish(); // Close login activity
                } else {
                    Toast.makeText(this, "Invalid Email or Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sign up button listener
        signUpText.setOnClickListener(v -> startActivity(new Intent(this, RegistrationActivity.class)));
    }

    // Validate inputs
    private boolean validateInputs(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Please enter your email/username");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter your password");
            return false;
        }
        return true;
    }

    // Authenticate user by checking both email and password in the database
    private boolean authenticateUser(String email, String password) {
        return databaseHelper.checkUser(email, password); // Call DB helper method
    }
}
