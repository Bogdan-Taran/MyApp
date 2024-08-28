package com.example.remotewifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RoomBogdanLight extends AppCompatActivity {
    // КНОПКИ ДЛЯ ИК НА ПОСТ
    Button lightroom_btn1;
    Button lightroom_btn2;
    Button lightroom_btn3;
    Button lightroom_btn4;
    Button lightroom_btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_bogdan_light);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // КНОПКИ ДЛЯ ИК НА ПОСТ
        lightroom_btn1 = (Button) findViewById(R.id.lightroom_btn1);
        lightroom_btn2 = (Button) findViewById(R.id.lightroom_btn2);
        lightroom_btn3 = (Button) findViewById(R.id.lightroom_btn3);
        lightroom_btn4 = (Button) findViewById(R.id.lightroom_btn4);
        lightroom_btn5 = (Button) findViewById(R.id.lightroom_btn5);



        // передаём строчки в другую активити
        lightroom_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Light_on = "IR_center_light_on";
                Intent send_string_to_Post1 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post1.putExtra("IR_1", Light_on);
                startActivity(send_string_to_Post1);
            }
        });
        lightroom_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Light_off = "IR_center_light_off";
                Intent send_string_to_Post2 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post2.putExtra("IR_2", Light_off);
                startActivity(send_string_to_Post2);
            }
        });
        lightroom_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RGB_on = "RGB_on";
                Intent send_string_to_Post3 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post3.putExtra("IR_3", RGB_on);
                startActivity(send_string_to_Post3);
            }
        });
        lightroom_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RGB_off = "RGB_off";
                Intent send_string_to_Post4 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post4.putExtra("IR_4", RGB_off);
                startActivity(send_string_to_Post4);
            }
        });
        lightroom_btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RGB_fade = "RGB_fade";
                Intent send_string_to_Post5 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post5.putExtra("IR_5", RGB_fade);
                startActivity(send_string_to_Post5);
            }
        });




    }
}