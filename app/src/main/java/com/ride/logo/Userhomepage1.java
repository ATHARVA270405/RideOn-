package com.ride.logo;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class Userhomepage1 extends AppCompatActivity {

    private Spinner pickupSpinner, dropSpinner, passengerSpinner;
    private Button calculateButton;

    private final double RATE_PER_KM = 4.40;
    private final double TIME_PER_KM = 1.5;

    private final HashMap<String, Integer> distanceMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhomepage1);

        pickupSpinner = findViewById(R.id.pickupSpinner);
        dropSpinner = findViewById(R.id.dropSpinner);
        passengerSpinner = findViewById(R.id.passengerSpinner);
        calculateButton = findViewById(R.id.calculateButton);

        // Define Distance Data
        distanceMap.put("Panchawati-Rajkamal", 5);
        distanceMap.put("Panchawati-Badnera", 12);
        distanceMap.put("Panchawati-Navsari", 7);
        distanceMap.put("Rajkamal-Panchawati", 5);
        distanceMap.put("Badnera-Panchawati", 12);
        distanceMap.put("Navsari-Panchawati", 7);

        // Setup Spinners
        String[] locations = {"Panchawati", "Rajkamal", "Rajapeth", "Nevsari", "Dasturnagar", "Badnera", "Sainagar"};
        String[] passengers = {"1", "2", "3", "4", "5"};

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        pickupSpinner.setAdapter(locationAdapter);
        dropSpinner.setAdapter(locationAdapter);

        ArrayAdapter<String> passengerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, passengers);
        passengerSpinner.setAdapter(passengerAdapter);

        calculateButton.setOnClickListener(v -> calculateFare());
    }

    private void calculateFare() {
        String pickup = pickupSpinner.getSelectedItem().toString();
        String drop = dropSpinner.getSelectedItem().toString();
        String passengers = passengerSpinner.getSelectedItem().toString();

        if (pickup.equals(drop)) {
            Toast.makeText(this, "Pickup and Drop cannot be the same!", Toast.LENGTH_SHORT).show();
            return;
        }

        String key = pickup + "-" + drop;
        Integer distance = distanceMap.get(key);

        if (distance == null) {
            Toast.makeText(this, "Distance data not available!", Toast.LENGTH_SHORT).show();
            return;
        }

        double fare = distance * RATE_PER_KM;
        double time = distance * TIME_PER_KM;

        String travelDetails = String.format(
                "Pickup: %s\nDrop: %s\nDistance: %d km\nTime: %.1f min\nFare: Rs %.2f\nPassengers: %s",
                pickup, drop, distance, time, fare, passengers
        );

        // Start new activity with calculated details
        Intent intent = new Intent(Userhomepage1.this, Userhomepage3.class);
        intent.putExtra("TRAVEL_DETAILS", travelDetails);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}