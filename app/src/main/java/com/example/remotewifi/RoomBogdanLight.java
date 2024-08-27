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



        // передаём строчку в другую активити
        lightroom_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent send_string_to_Post1 = new Intent(RoomBogdanLight.this, ActivityMyRoomBogdan.class);
                send_string_to_Post1.putExtra("IR_1", "IR_center_light");
            }
        });


    }
}