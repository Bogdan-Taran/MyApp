// расстояние вьюшек на экране равно = 16 (расстояние top первой вьюшки) + 50 (высота 1 вьюшки) + 32 (расстояние до второй вьюшки снизу)

package com.example.remotewifi;



import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.remotewifi.SwitchCustomWithText;




public class ActivityMyRoomBogdan extends AppCompatActivity {

    private SwitchCustomWithText customSwitch;
    private Button button_back_from_room_bogdan_to_main_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_bogdan);
        button_back_from_room_bogdan_to_main_home = findViewById(R. id. button_back_from_room_bogdan_to_main_home);

        // Скрываем status bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Инициализация элементов
        //customSwitch = findViewById(R.id.switchButton);


//        Ставим кликер на кнопку чтобы перебрасывало на главный экрна
        button_back_from_room_bogdan_to_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_from_room_bogdan_to_main_home = new Intent(ActivityMyRoomBogdan.this, TheHomeActivity.class);
                startActivity(intent_from_room_bogdan_to_main_home);finish();
            }
        });




    }






}