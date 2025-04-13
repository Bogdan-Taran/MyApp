package com.example.remotewifi;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PresetActivity extends AppCompatActivity {

    private ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preset);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        backgroundImage = findViewById(R.id.img_background_for_activity_preset);

        Button btn_turn_on_all_light = findViewById(R.id.btn_turn_on_off_all_light);

        Button btn_turn_on_all_podsvetka_stol = findViewById(R.id.btn_turn_on_off_all_podsvetka_stol);

        Button btn_end_of_the_film = findViewById(R.id.btn_end_of_the_film);
        Button btn_turn_everything_off = findViewById(R.id.btn_turn_everything_off);

        blurBackgroundWithGlide();

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