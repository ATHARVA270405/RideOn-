package com.ride.logo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ride.logo.R;

public class next_ride extends AppCompatActivity {

    // Declare view components
    ImageView mapView, profileImage, userIcon;
    TextView fareAmount, userName, distanceTime, rating, pickupLabel, pickupLocation;
    LinearLayout fareContainer, requestCard;
    Button acceptButton, rejectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_home); // Reference to the XML layout file

        // Initialize views
        mapView = findViewById(R.id.mapView);
        profileImage = findViewById(R.id.profileImage);
        userIcon = findViewById(R.id.userIcon);

        fareAmount = findViewById(R.id.fareAmount);
        userName = findViewById(R.id.userName);
        distanceTime = findViewById(R.id.distanceTime);
        rating = findViewById(R.id.rating);
        pickupLabel = findViewById(R.id.pickupLabel);
        pickupLocation = findViewById(R.id.pickupLocation);

        fareContainer = findViewById(R.id.fareContainer);
        requestCard = findViewById(R.id.requestCard);

        acceptButton = findViewById(R.id.acceptButton);
        rejectButton = findViewById(R.id.rejectButton);

        // Set example data (optional, for testing)
        setupExampleData();
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(next_ride.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Set button listeners
        acceptButton.setOnClickListener(v ->
                Toast.makeText(next_ride.this, "Ride Accepted", Toast.LENGTH_SHORT).show()
        );

        rejectButton.setOnClickListener(v ->
                Toast.makeText(next_ride.this, "Ride Rejected", Toast.LENGTH_SHORT).show()
        );
    }


    // Function to populate example data (optional)
    private void setupExampleData() {
        fareAmount.setText("₹ 558.63");
        userName.setText("Shubham Kulkarni");
        distanceTime.setText("2 kms away | 8 mins");
        rating.setText("⭐ 4.90");
        pickupLabel.setText("Pickup from");
        pickupLocation.setText("Lilawati villa, swarkar Nagar, Nashik,Maharashtra");
    }
}