// расстояние вьюшек на экране равно = 16 (расстояние top первой вьюшки) + 50 (высота 1 вьюшки) + 32 (расстояние до второй вьюшки снизу)

package com.example.remotewifi;


import static com.example.remotewifi.R.id.btn_podsvetka_stol;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ActivityMyRoomBogdan extends AppCompatActivity {
    ConsumerIrManager IrMan;
    private static final int[] Rem_ON = {4600,4350,700,1550,650,1550,650,1600,650,450,650,450,650,450,650,450,700,400,700,1550,650,1550,650,1600,650,450,650,450,650,450,700,450,650,450,650,450,650,1550,700,450,650,450,650,450,650,450,650,450,700,400,650,1600,650,450,650,1550,650,1600,650,1550,650,1550,700,1550,650,1550,650};
    //OFF
    private static final int[] Off = {9200,4450, 650,450, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 700,450, 650,1600, 650,1600, 700,1600, 650,1600, 650,500, 600,1650, 650,1600, 650,1600, 700,450, 700,1550, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 650,1600, 700,450, 650,1600, 700,1550, 700,1600, 650,1600, 650,1600, 700,1600, 650};
    //ON
    private static final int[] ON = {9250,4400, 650,500, 650,500, 600,500, 650,500, 650,500, 650,450, 650,500, 700,450, 650,1600, 650,1600, 700,1600, 600,1650, 650,500, 650,1600, 650,1600, 700,1600, 600,1650, 650,1600, 700,450, 650,500, 650,450, 700,450, 650,500, 600,500, 700,450, 650,500, 650,1600, 700,1550, 650,1650, 650,1600, 650,1600, 700,1600, 600};
    //(1,4)


    private Request request;    // добавляем переменную для запросов (OkHttp3)
    Button podsvetka_stol;
    Button svetilnik_stol;
    SharedPreferences pref;
    TextView podsvetkaStolTextView;

    Button btnOnRGBLenta;
    Button btnOffRGBLenta;
    Button btnUpRGBLenta;
    Button btnDownRGBLenta;
    Button btnFlashRGBLenta;
    Button btnStrobeRGBLenta;
    Button btnFadeRGBLenta;
    Button btnSmoothRGBLenta;
    Button btnRedRGBLenta;
    Button btnGreenRGBLenta;
    Button btnBlueRGBLenta;
    Button btnWhiteRGBLenta;
    Button btnOrangeRGBLenta;
    Button btnLgreenRGBLenta;
    Button btnMblueRGBLenta;
    Button btnMredRGBLenta;
    Button btnSkyblueRGBLenta;
    Button btnPurpleRGBLenta;
    Button btnLorangeRGBLenta;
    Button btnMMblueRGBLenta;
    Button btnMpurpleRGBLenta;
    Button btnYellowRGBLenta;
    Button btnSeaBlueRGBLenta;


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

        IrMan = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        btnOnRGBLenta = findViewById(R.id.btn_rgb_lenta_on);
        btnOffRGBLenta = findViewById(R.id.btn_rgb_lenta_off);


        btnOnRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, ON);
                }else {
                    Toast.makeText(ActivityMyRoomBogdan.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnOffRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Off);
                }else{
                    Toast.makeText(ActivityMyRoomBogdan.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }

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

        podsvetka_stol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("podsvetka_stol");

            }
        });

        svetilnik_stol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("svetilnik_stol");
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