package com.example.remotewifi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.google.android.material.card.MaterialCardView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Objects;

import jp.wasabeef.glide.transformations.BlurTransformation; // Импортируем BlurTransformation

public class TheHomeActivity extends AppCompatActivity {

    Button circle_room_bogdan;

    private MaterialCardView customCircle;
    private ImageView backgroundImage;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_the_home);




        // Скрываем status bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        // Убеждаемся, что контент не уходит под status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Инициализация MaterialCardView
        customCircle = findViewById(R.id.circle_the_home_activity);
        // Настраиваем размытую обводку
        applyBlurredStroke();

        backgroundImage = findViewById(R.id.background_image_the_home_activity);
        blurBackgroundWithGlide();



        circle_room_bogdan = findViewById(R.id.circle_room_bogdan);
//        circle_room_bogdan.setOnClickListener(v -> {
//            Intent intent_to_room_bogdan = new Intent(TheHomeActivity.this, ActivityDevicesAndPresets.class);
//            startActivity(intent_to_room_bogdan);finish();
//        });
//
        circle_room_bogdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_room_bogdan = new Intent(TheHomeActivity.this, ActivityDevicesAndPresets.class);
                startActivity(intent_to_room_bogdan);finish();
            }
        });


    }


    private void applyBlurredStroke() {
        // Создаем Paint для обводки
        Paint strokePaint = new Paint();
        strokePaint.setColor(Color.parseColor("#A59797")); // Цвет обводки
        strokePaint.setStyle(Paint.Style.STROKE); // Режим обводки
        strokePaint.setStrokeWidth(25f); // Ширина обводки
        strokePaint.setMaskFilter(new BlurMaskFilter(8f, BlurMaskFilter.Blur.NORMAL)); // Размытие 10dp

        // Создаем Drawable для обводки
        Drawable background = customCircle.getBackground();
        if (background != null) {
            customCircle.setBackground(null); // Удаляем старый фон
        }

        // Настраиваем кастомный фон с размытой обводкой (упрощенный подход)
        customCircle.setForeground(new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                float centerX = customCircle.getWidth() / 2f;
                float centerY = customCircle.getHeight() / 2f;
                float radius = Math.min(centerX, centerY) - 5f; // Уменьшаем радиус для обводки

                canvas.drawCircle(centerX, centerY, radius, strokePaint);
            }

            @Override
            public void setAlpha(int alpha) {}

            @Override
            public void setColorFilter(android.graphics.ColorFilter colorFilter) {}

            @Override
            public int getOpacity() {
                return android.graphics.PixelFormat.TRANSLUCENT;
            }
        });
    }

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
