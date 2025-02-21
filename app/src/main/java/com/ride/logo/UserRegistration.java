package com.ride.logo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegistration extends AppCompatActivity {
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregistration);

        dbHelper = new UserDatabaseHelper(this);

        EditText firstName = findViewById(R.id.FirstName);
        EditText lastname = findViewById(R.id.LastName);
        EditText email = findViewById(R.id.Email);
        EditText password = findViewById(R.id.Password);
        EditText confirmPassword = findViewById(R.id.ConfirmPassword);
        Button signUpButton = findViewById(R.id.SignUpButton);
        ImageView backButton = findViewById(R.id.BackButton);
        TextView loginLink = findViewById(R.id.loginLink);

        loginLink.setOnClickListener(view -> {
            Intent intent = new Intent(UserRegistration.this, Userside.class);
            startActivity(intent);
            finish();
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(UserRegistration.this, Userside.class);
            startActivity(intent);
            finish();
        });

        signUpButton.setOnClickListener(v -> {
            String name = firstName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();

            if (name.isEmpty() || mail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(UserRegistration.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else if (!pass.equals(confirmPass)) {
                Toast.makeText(UserRegistration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.checkUserExists(mail)) {
                Toast.makeText(UserRegistration.this, "User already exists", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = dbHelper.registerUser(name, mail, pass);
                if (isInserted) {
                    Toast.makeText(UserRegistration.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserRegistration.this, Userside.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserRegistration.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
