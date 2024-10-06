package com.example.remotewifi;

import static android.icu.lang.UCharacter.DecompositionType.VERTICAL;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RoomBogdanLouver extends AppCompatActivity {
    String[] data_for_dropdown = {"Нет", "Задать время"};

    private Spinner scheduleSpinner;
    SharedPreferences pref_save_choice;
    EditText edittextInputHour;
    EditText edittextInputMinute;

    private Request request;    // добавляем переменную для запросов (OkHttp3)
    SharedPreferences pref;
    TextView textview_setTheTime;
    String send_time_numbers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_bogdan_louver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout linear_layout_set_time = (LinearLayout) findViewById(R.id.linear_layout_set_time);
        pref_save_choice = getSharedPreferences("Save_Choice", MODE_PRIVATE);     // создаём внутреннее хранилище


        edittextInputHour = (EditText) findViewById(R.id.edittextInputHour);
        edittextInputMinute = (EditText) findViewById(R.id.edittextInputMinute);
        ImageButton btn_send_time = (ImageButton) findViewById(R.id.btn_send_time);
        paste_hourAndMinute();

        textview_setTheTime = (TextView) findViewById(R.id.textview_setTheTime);

        btn_send_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(!edittextInputHour.getText().toString().isEmpty() && !edittextInputMinute.getText().toString().isEmpty()) {      // проверяем наши edittext а пустоту чтобыне вылазила ошибка
                    Integer send_hour = Integer.parseInt(edittextInputHour.getText().toString());
                    Integer send_minunte = Integer.parseInt(edittextInputMinute.getText().toString());

                    SharedPreferences.Editor pref_edit = pref_save_choice.edit();   // включаем функцию принятия изменений
                    pref_edit.putInt("send_hour", send_hour);      // складываем число-позицию под ключом selectedUserCoice
                    pref_edit.putInt("send_minunte", send_minunte);      // складываем число-позицию под ключом selectedUserCoice
                    pref_edit.apply();      // применяем изменения

                    post("start");  // отправляем на сервер команду подготовиться к принятию чисел

                    int time_number_1 = pref_save_choice.getInt("send_hour", 0);
                    int time_number_2 = pref_save_choice.getInt("send_minunte", 0);
                    send_time_numbers = time_number_1 + ":" + time_number_2;

                    post(send_time_numbers);


                }
                Toast.makeText(RoomBogdanLouver.this, "Время открытия жаллюзей установлено на " + pref_save_choice.getInt("send_hour", 0) + ":" + pref_save_choice.getInt("send_minunte", 0), Toast.LENGTH_LONG).show();








            }
        });









        //ИНТЕНТЫ
        ImageButton btn_backdpace = (ImageButton) findViewById(R.id.btn_backdpace);
        btn_backdpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back_to_my_room_Bogdan = new Intent(RoomBogdanLouver.this, ActivityMyRoomBogdan.class);
                startActivity(intent_back_to_my_room_Bogdan);finish();
            }
        });

        ImageButton btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_my_home = new Intent(RoomBogdanLouver.this, TheHomeActivity.class);
                startActivity(intent_to_my_home);finish();
            }
        });



        // выпадающий список
        scheduleSpinner = (Spinner) findViewById(R.id. scheduleSpinner);

        ArrayAdapter<String> adapter_for_dropdown = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_for_dropdown);
        adapter_for_dropdown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        scheduleSpinner.setAdapter(adapter_for_dropdown);
        scheduleSpinner.setPrompt("Открыть жалюзи по времени");
        scheduleSpinner.setSelection(pref_save_choice.getInt("userChoice", 0));



        scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "Ваш выбор: " + data_for_dropdown[position], Toast.LENGTH_SHORT).show();
//
                SharedPreferences.Editor pref_edit = pref_save_choice.edit();   // включаем функцию принятия изменений
                pref_edit.putInt("userChoice", position);      // складываем число-позицию под ключом selectedUserCoice
                pref_edit.apply();      // применяем изменения



                if(position == 1){
                   linear_layout_set_time.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                   linear_layout_set_time.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                   linear_layout_set_time.requestLayout();
                }
                else {
                    linear_layout_set_time.getLayoutParams().height = 0;
                    linear_layout_set_time.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                    linear_layout_set_time.requestLayout();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void paste_hourAndMinute(){
        int hour = pref_save_choice.getInt("send_hour", 0);
        int minute = pref_save_choice.getInt("send_minunte", 0);

        if(hour != -1 && minute != -1){
            edittextInputHour.setText(String.valueOf(hour));
            edittextInputMinute.setText(String.valueOf(minute));
        }
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