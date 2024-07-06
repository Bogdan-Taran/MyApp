package com.example.remotewifi;

import static com.example.remotewifi.R.id.btn_podsetka_stol;

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


public class ActivityMyRoomBogdan extends AppCompatActivity {
    private Request request;    // добавляем переменную для запросов (OkHttp3)
    ActivityTheHomeBinding binding;
    ActivityMyRoomBogdanBinding binding_btns;
    private OkHttpClient client;
    private static final int onOffPodsvetkaStol = btn_podsetka_stol;

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



        TextView trans_rgb_lenta = findViewById(R.id.rgb_lenta);
        trans_rgb_lenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_rgb_place = new Intent(ActivityMyRoomBogdan.this, ActivityRoomBogdanRGB.class);
                startActivity(intent_to_rgb_place);
            }
        });

        binding_btns.btnPodsetkaStol.setOnClickListener(onClickPostData());

    }

    private View.OnClickListener onClickPostData() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == onOffPodsvetkaStol){
                    post("podsvetkaStol");
                }
            }
        };
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