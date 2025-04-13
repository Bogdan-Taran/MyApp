// расстояние вьюшек на экране равно = 16 (расстояние top первой вьюшки) + 50 (высота 1 вьюшки) + 32 (расстояние до второй вьюшки снизу)

package com.example.remotewifi;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.remotewifi.SwitchCustomWithText;

import jp.wasabeef.glide.transformations.BlurTransformation;



public class ActivityMyRoomBogdan extends AppCompatActivity {

    private SwitchCustomWithText customSwitch;
    private Button button_back_from_room_bogdan_to_main_home;
    private ImageView backgroundImage;



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
        backgroundImage = findViewById(R.id.img_background_for_activity_preset);


        blurBackgroundWithGlide();

//        Ставим кликер на кнопку чтобы перебрасывало на главный экрна
        button_back_from_room_bogdan_to_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_from_room_bogdan_to_main_home = new Intent(ActivityMyRoomBogdan.this, TheHomeActivity.class);
                startActivity(intent_from_room_bogdan_to_main_home);finish();
            }
        });




    }

//    размытие бэкграунда
    private void blurBackgroundWithGlide() {
        Glide.with(this)
                .load(R.drawable.background_room) // Загружаем изображение
                .transform(new BlurTransformation(10)) // Размытие с радиусом 25
                .into(new CustomTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        backgroundImage.setImageDrawable(resource);
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        // Ничего не делаем при очистке
                    }
                });
    }

}