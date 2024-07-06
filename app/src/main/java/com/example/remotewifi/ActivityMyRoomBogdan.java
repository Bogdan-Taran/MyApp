package com.example.remotewifi;

import static com.example.remotewifi.R.id.btn_podsvetka_stol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remotewifi.databinding.ActivityMainBinding;
import com.example.remotewifi.databinding.ActivityMyRoomBogdanBinding;
import com.example.remotewifi.databinding.ActivityTheHomeBinding;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ActivityMyRoomBogdan extends AppCompatActivity  {
    private Request request;    // добавляем переменную для запросов (OkHttp3)
    ActivityTheHomeBinding binding;
    ActivityMyRoomBogdanBinding binding_btns;
    private OkHttpClient client;
    public static final int BTN_PODSVETKA_STOL_ID = R.id.btn_podsvetka_stol;
    public static final int BTN_SVETILNIK_STOL_ID = R.id.btn_svetilnik_stol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_room_bogdan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button podsvetkaStol = (Button) findViewById(btn_podsvetka_stol);
        Button svetilnikStol = (Button) findViewById(R.id.btn_svetilnik_stol);

        podsvetkaStol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("Podsvetka_Stol");
            }
        });
        svetilnikStol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("Svetilnik_Stol");
            }
        });



        TextView trans_rgb_lenta = findViewById(R.id.rgb_lenta);
        trans_rgb_lenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_rgb_place = new Intent(ActivityMyRoomBogdan.this, ActivityRoomBogdanRGB.class);
                startActivity(intent_to_rgb_place);
            }
        });



    }





    private void post(String post) {
        new Thread(new Runnable() { // новый второстепенный поток
            @Override
            public void run() {     // второст. поток

                request = new Request.Builder().url("http://" + binding.editTextSaveIp.getText() + "/" + post).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String resultText = response.body().string();   // это для получения данных с ESP - там могут быть датчики температуры и пр. Пока задействовать не будем
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start(); // запускаем второстепенный поток
    }

}