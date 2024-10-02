package com.example.remotewifi;

import static android.icu.lang.UCharacter.DecompositionType.VERTICAL;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.Intent;
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

public class RoomBogdanLouver extends AppCompatActivity {
    String[] data_for_dropdown = {"Нет", "Задать время", "Автоматически по рассвету"};

    private Spinner scheduleSpinner;
    private Button openScheduleButton;
    private LinearLayout timeInputLayout;
    private EditText inputHourSchedule, inputMinuteSchedule;

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



        final container = findViewById(R.id.container);
        final LayoutInflater inflater = getLayoutInflater();

        Button button = findViewbyId(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(inflater, container)
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
        scheduleSpinner.setSelection(0);

        scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Ваш выбор: " + data_for_dropdown[position], Toast.LENGTH_SHORT).show();
                if(position == 1){
                    LinearLayout layout1 = new LinearLayout(getApplicationContext());
                    layout1.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layout1params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
                    layout1params.setMargins(15, 364, 20, 0);
                    layout1.setBackgroundColor(16777215);

                    Button okButton=new Button(getApplicationContext());
                    okButton.setText("some text");
                    layout1.addView(okButton, layout1params);




                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Button btn_both_louver_open = (Button) findViewById(R.id.btn_both_louver_open);
        btn_both_louver_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout layout1 = new LinearLayout(getBaseContext());
                layout1.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams layout1params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
                layout1params.setMargins(15, 364, 20, 0);
                layout1.setBackgroundColor(16777215);

                Button okButton=new Button(getBaseContext());
                okButton.setText("some text");
                layout1.addView(okButton, layout1params);
            }
        });













    }





}