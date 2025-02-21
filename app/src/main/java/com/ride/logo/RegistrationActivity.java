package com.ride.logo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextName, editTextNumber, editTextPassword, editTextVehicleNumber, editTextLicenseNumber;
    private Spinner spinnerVehicleType;
    private Button buttonRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrationactivity);

        databaseHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.Name);
        editTextNumber = findViewById(R.id.Phone);
        editTextPassword = findViewById(R.id.Password);
        editTextVehicleNumber = findViewById(R.id.VehicleNumber);
        editTextLicenseNumber = findViewById(R.id.LicenseNumber);
        spinnerVehicleType = findViewById(R.id.SpinnerVehicleType);
        buttonRegister = findViewById(R.id.Registerbutton);
        TextView loginLink = findViewById(R.id.loginback);
        ImageView backButton1 = findViewById(R.id.backButton2);

        loginLink.setOnClickListener(view -> {
            startActivity(new Intent(RegistrationActivity.this, Driverside.class));
            finish();
        });

        backButton1.setOnClickListener(view -> {
            startActivity(new Intent(RegistrationActivity.this, Driverside.class));
            finish();
        });

        // Set up the spinner with vehicle types
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVehicleType.setAdapter(adapter);



        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            String number = editTextNumber.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String vehicleNumber = editTextVehicleNumber.getText().toString().trim();
            String licenseNumber = editTextLicenseNumber.getText().toString().trim();
            String vehicleType = spinnerVehicleType.getSelectedItem().toString();

            if (name.isEmpty() || number.isEmpty() || password.isEmpty() || vehicleNumber.isEmpty() || licenseNumber.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if phone number already exists
            if (databaseHelper.isPhoneExists(number)) {
                Toast.makeText(RegistrationActivity.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean insertSuccess = databaseHelper.insertUser(name, number, password, vehicleNumber, licenseNumber, vehicleType);
            if (insertSuccess) {
                Toast.makeText(RegistrationActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, Driverside.class));
                finish();
            } else {
                Toast.makeText(RegistrationActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
