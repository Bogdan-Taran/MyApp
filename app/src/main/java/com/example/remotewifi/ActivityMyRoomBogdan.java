package com.example.remotewifi;


import static com.example.remotewifi.R.id.btn_podsvetka_stol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class ActivityMyRoomBogdan extends AppCompatActivity {
    private Request request;    // добавляем переменную для запросов (OkHttp3)
    Button podsvetka_stol;
    Button svetilnik_stol;
    SharedPreferences pref;
    TextView podsvetkaStolTextView;

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

    podsvetka_stol = (Button) findViewById(btn_podsvetka_stol);
    svetilnik_stol = (Button) findViewById(R.id.btn_svetilnik_stol);
    podsvetkaStolTextView = (TextView) findViewById(R. id. podsetka_stol);


        TextView trans_rgb_lenta = findViewById(R.id.rgb_lenta);
        trans_rgb_lenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_rgb_place = new Intent(ActivityMyRoomBogdan.this, ActivityRoomBogdanRGB.class);
                startActivity(intent_to_rgb_place);
            }
        });

        podsvetka_stol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("podsvetka_stol");

            }
        });

    }

    private void post(String post) {
        new Thread(new Runnable() { // новый второстепенный поток
            @Override
            public void run() {     // второст. поток
                pref = getSharedPreferences("IP_ESP", MODE_PRIVATE);
                String ip_edText = pref.getString("ip", "192.168.1.41");
                OkHttpClient client = new OkHttpClient();


                request = new Request.Builder().url("http://" + ip_edText + "/" + post).build();
                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        String resultText = response.body().string();   // это для получения данных с ESP - там могут быть датчики температуры и пр. Пока задействовать не будем

                    }

                } catch (IOException e) {
                    e.printStackTrace();

                }


            }
        }).start(); // запускаем второстепенный поток
    }

}