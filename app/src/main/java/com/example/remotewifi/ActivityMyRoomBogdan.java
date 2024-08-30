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

    private static final int[] Center_lgth_on = {9032, 4464,  596, 1648,  592, 560,  572, 560,  572, 560,  572, 560,  572, 560,  572, 560,  572, 560,  572, 560,  572, 1644,  596, 1644,  594, 1646,  594, 1644,  596, 1646,  594, 1646,  594, 1644,  596, 558,  572, 560,  574, 558,  574, 560,  574, 560,  572, 560,  572, 560,  572, 560,  572, 1646,  592, 1646,  594, 1644,  596, 1644,  596, 1644,  596, 1644,  594, 1646,  594, 1646,  594, 39742,  9034, 2226,  594};
    private static final int[] Center_lght_off = {9034, 4464,  594, 1646,  594, 560,  572, 560,  572, 560,  572, 560,  572, 560,  572, 560,  574, 560,  572, 560,  572, 1646,  594, 1644,  596, 1644,  596, 1644,  596, 1644,  596, 1644,  596, 1644,  596, 1646,  594, 1646,  596, 558,  574, 560,  572, 560,  574, 558,  574, 558,  574, 560,  574, 560,  574, 560,  574, 1644,  596, 1644,  596, 1644,  596, 1646,  594, 1644,  596, 1644,  596, 39734,  9032, 2222,  596};  // NEC 807FC03F

    private static final int[] IRprojector_Epson_on = {972, 390,  290, 942,  296, 388,  292, 392,  290, 684,  290, 392,  290, 1198,  298, 388,  290, 392,  290, 940,  298, 678,  290, 684,  290, 682,  292, 682,  292, 684,  290, 392,  292, 392,  290, 392,  290, 392,  290, 392,  290, 682,  290, 940,  294, 938,  294, 390,  290};  // UNKNOWN 4554CC01
    private static final int[] IRprojector_Epson_off = {972, 390,  290, 942,  296, 388,  292, 392,  290, 684,  290, 392,  290, 1198,  298, 388,  290, 392,  290, 940,  298, 678,  290, 684,  290, 682,  292, 682,  292, 684,  290, 392,  292, 392,  290, 392,  290, 392,  290, 392,  290, 682,  290, 940,  294, 938,  294, 390,  290};  // UNKNOWN 4554CC01



    private Request request;    // добавляем переменную для запросов (OkHttp3)
    Button podsvetka_stol;
    Button svetilnik_stol;
    Button girlanda_stena;
    Button louver;
    Button center_light_on;
    Button center_light_off;
    TextView btn_to_lightroom;
    Button projector_Epson_on;
    Button projector_Epson_off;

    SharedPreferences pref;
    TextView podsvetkaStolTextView;
    TextView text_top_my_home;

    Button btnOnRGBLenta;
    Button btnOffRGBLenta;







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
        girlanda_stena = (Button) findViewById(R.id.btn_girlanda_stena);
        louver = (Button) findViewById(R.id.btn_louver);
        center_light_on = (Button) findViewById(R.id.center_light_on);
        center_light_off = (Button) findViewById(R.id.center_light_off);
        btn_to_lightroom = (TextView) findViewById(R.id. center_light);
        projector_Epson_on = (Button) findViewById(R.id. projector_Epson_on);
        projector_Epson_off = (Button) findViewById(R.id. projector_Epson_off);

        // ДЛЯ ИК
        IrMan = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        btnOnRGBLenta = findViewById(R.id.btn_rgb_lenta_on);
        btnOffRGBLenta = findViewById(R.id.btn_rgb_lenta_off);
        TextView trans_rgb_lenta = findViewById(R.id.rgb_lenta);

        //проверка
        text_top_my_home = (TextView) findViewById(R.id. text_top_my_room);




        // ИК СИГНАЛЫ
        btnOnRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("RGB_on");
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, ON);
                }else {
                    return;
                }

            }
        });
        btnOffRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("RGB_off");
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Off);
                }else{
                    return;
                }

            }
        });
        center_light_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("IR_center_light_on");
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Center_lgth_on);
                }else{
                    return;
                }

            }
        });
        center_light_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("IR_center_light_off");
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000,Center_lght_off);
                }else{
                    return;
                }

            }
        });
        projector_Epson_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000,IRprojector_Epson_on);
                }else{
                    Toast.makeText(ActivityMyRoomBogdan.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
                post("projector_Epson_on");
            }
        });
        projector_Epson_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000,IRprojector_Epson_off);
                }else{
                    Toast.makeText(ActivityMyRoomBogdan.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
                post("projector_Epson_off");

            }
        });



        // ИНТЕНТЫ

//        btn_to_lightroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_to_lightroom_place = new Intent(ActivityMyRoomBogdan.this, RoomBogdanLight.class);
//                startActivity(intent_to_lightroom_place);
//            }
//        });

        trans_rgb_lenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_rgb_place = new Intent(ActivityMyRoomBogdan.this, ActivityRoomBogdanRGB.class);
                startActivity(intent_to_rgb_place);
            }
        });

        TextView btn_to_presets = findViewById(R.id.btn_to_presets);
        btn_to_presets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_presets = new Intent(ActivityMyRoomBogdan.this, PresetActivity.class);
                startActivity(intent_to_presets);
            }
        });

        //Принимаем интенты пресетов

        String TurnOnAllLight = getIntent().getStringExtra("TurnOnAllLight");
        post(TurnOnAllLight);

        String TurnOffAllLight = getIntent().getStringExtra("TurnOffAllLight");
        post(TurnOffAllLight);

        String TurnOnPodsvStol = getIntent().getStringExtra("TurnOnPodsvStol");
        post(TurnOnPodsvStol);

        String TurnOffPodsvStol = getIntent().getStringExtra("TurnOffPodsvStol");
        post(TurnOffPodsvStol);

        String EndFilm = getIntent().getStringExtra("EndFilm");
        post(EndFilm);

        String EverythOff = getIntent().getStringExtra("EverythOff");
        post(EverythOff);




        //Принимаем строчки из активити главного света
        //Intent send_string_to_Post1 = getIntent();
//        String IR_1_1 = getIntent().getStringExtra("IR_1");
//        post(IR_1_1);
//
//        String IR_2_2 = getIntent().getStringExtra("IR_2");
//        post(IR_2_2);
//
//        String IR_3_3 = getIntent().getStringExtra("IR_3");
//        post(IR_3_3);
//
//        String IR_4_4 = getIntent().getStringExtra("IR_4");
//        post(IR_4_4);
//
//        String IR_5_5 = getIntent().getStringExtra("IR_5");
//        post(IR_5_5);






        // ПОСТЫ
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
        girlanda_stena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("girlanda_stena");
            }
        });
        louver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("empty");
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