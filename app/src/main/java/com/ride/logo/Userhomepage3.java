package com.ride.logo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Userhomepage3 extends AppCompatActivity {

    private TextView travelTime;
    private TextView travelDistance;
    private TextView address;
    private Button bookNowButton;
    private Button navigationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhomepage3);

        // Initialize Views
        travelTime = findViewById(R.id.travelTime);
        travelDistance = findViewById(R.id.travelDistance);
        address = findViewById(R.id.address);
        bookNowButton = findViewById(R.id.bookNowButton);
        navigationButton = findViewById(R.id.navigationButton);

        // Mock Data (Can be fetched dynamically)
        travelTime.setText("28 min");
        travelDistance.setText("2 Km");
        address.setText("Manila Oriental, Glen Park");

        // Book Now Button Click Listener
        bookNowButton.setOnClickListener(v ->
                Toast.makeText(Userhomepage3.this, "Booking Confirmed!", Toast.LENGTH_SHORT).show()
        );

        // Navigation Button Click Listener
        navigationButton.setOnClickListener(v ->
                Toast.makeText(Userhomepage3.this, "Starting Navigation...", Toast.LENGTH_SHORT).show()
        );
    }
}
