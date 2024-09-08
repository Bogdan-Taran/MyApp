package com.example.remotewifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PresetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preset);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_turn_on_all_light = findViewById(R.id.btn_turn_on_all_light);
        Button btn_turn_off_all_light = findViewById(R.id.btn_turn_off_all_light);
        Button btn_turn_on_all_podsvetka_stol = findViewById(R.id.btn_turn_on_all_podsvetka_stol);
        Button btn_turn_off_all_podsvetka_stol = findViewById(R.id.btn_turn_off_all_podsvetka_stol);
        Button btn_end_of_the_film = findViewById(R.id.btn_end_of_the_film);
        Button btn_turn_everything_off = findViewById(R.id.btn_turn_everything_off);

        //Интенты
        btn_turn_on_all_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TurnOnAllLight = "btn_turn_on_all_light";
                Intent intentTurnOnAllLight = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentTurnOnAllLight.putExtra("TurnOnAllLight", TurnOnAllLight);
                startActivity(intentTurnOnAllLight);finish();
            }
        });
        btn_turn_off_all_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TurnOffAllLight = "btn_turn_off_all_light";
                Intent intentTurnOffAllLight = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentTurnOffAllLight.putExtra("TurnOffAllLight", TurnOffAllLight);
                startActivity(intentTurnOffAllLight);finish();
            }
        });


        btn_turn_on_all_podsvetka_stol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TurnOnPodsvStol = "btn_turn_on_all_podsvetka_stol";
                Intent intentTurnOnPodsvStol = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentTurnOnPodsvStol.putExtra("TurnOnPodsvStol", TurnOnPodsvStol);
                startActivity(intentTurnOnPodsvStol);finish();
            }
        });

        btn_turn_off_all_podsvetka_stol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TurnOffPodsvStol = "btn_turn_off_all_podsvetka_stol";
                Intent intentTurnOffPodsvStol = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentTurnOffPodsvStol.putExtra("TurnOffPodsvStol", TurnOffPodsvStol);
                startActivity(intentTurnOffPodsvStol);finish();
            }
        });



        btn_end_of_the_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EndFilm = "btn_end_of_the_film";
                Intent intentEndFilm = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentEndFilm.putExtra("EndFilm", EndFilm);
                startActivity(intentEndFilm);finish();
            }
        });

        btn_turn_everything_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EverythOff = "btn_turn_everything_off";
                Intent intentEverythOff = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                intentEverythOff.putExtra("EverythOff", EverythOff);
                startActivity(intentEverythOff);finish();
            }
        });

        ImageButton btn_backdpace = (ImageButton) findViewById(R.id.btn_backdpace);
        btn_backdpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(PresetActivity.this, ActivityMyRoomBogdan.class);
                startActivity(intent_back);finish();
            }
        });

        ImageButton btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(PresetActivity.this, TheHomeActivity.class);
                startActivity(intent_home);finish();
            }
        });

    }
}