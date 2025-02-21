package com.ride.logo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewName, textViewPhone, textViewVehicleNumber, textViewLicenseNumber;
    private DatabaseHelper databaseHelper;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        databaseHelper = new DatabaseHelper(this);

        textViewName = findViewById(R.id.etName);
        btnSave = findViewById(R.id.button21);
        textViewPhone = findViewById(R.id.etPhone);
        textViewVehicleNumber = findViewById(R.id.etvehicle);
        textViewLicenseNumber = findViewById(R.id.etEmail);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfileActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        // Fetch the last registered user
        Cursor cursor = databaseHelper.getLastRegisteredUser();

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    // Debugging: Print all available column names
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        System.out.println("Column " + i + ": " + cursor.getColumnName(i));
                    }

                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                    String vehicleNumber = cursor.getString(cursor.getColumnIndexOrThrow("vehicle_number"));
                    String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));  // Fetch email instead of license number

                    // Set the text fields
                    textViewName.setText(name);
                    textViewPhone.setText(phone);
                    textViewVehicleNumber.setText(vehicleNumber);
                    textViewLicenseNumber.setText(email);
                } else {
                    Toast.makeText(this, "No user data found!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error retrieving data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace(); // Print the error in logs for debugging
            } finally {
                cursor.close(); // Close cursor after use
            }
        } else {
            Toast.makeText(this, "Database query returned null!", Toast.LENGTH_LONG).show();
        }
    }
}
