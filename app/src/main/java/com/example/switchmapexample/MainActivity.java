package com.example.switchmapexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button button;
    Switch location_services, get_location, send_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        location_services = findViewById(R.id.location_services);
        send_location = findViewById(R.id.send_location);
        get_location = findViewById(R.id.get_location);

        // Butona tıklama olayını burada tanımlıyoruz
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!location_services.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Location services is close!", Toast.LENGTH_SHORT).show();
                } else {
                    if (send_location.isChecked() && get_location.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Location send and get is open!", Toast.LENGTH_SHORT).show();
                    } else if (send_location.isChecked() && !get_location.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Location send open and get is close!", Toast.LENGTH_SHORT).show();
                    } else if (!send_location.isChecked() && get_location.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Location send close and get is open!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Location send and get is close!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Switch değişimlerini burada dinliyoruz
        location_services.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    get_location.setVisibility(View.VISIBLE);
                    send_location.setVisibility(View.VISIBLE);
                } else {
                    get_location.setVisibility(View.INVISIBLE);
                    send_location.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
