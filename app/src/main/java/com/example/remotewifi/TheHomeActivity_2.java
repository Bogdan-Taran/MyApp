package com.example.remotewifi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TheHomeActivity_2 extends AppCompatActivity {

    private CardView circle1, circle2, circle3, circle4, circle5;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_the_home2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        // Инициализация элементов
        circle1 = findViewById(R.id.circle1);
        circle2 = findViewById(R.id.circle2);
        circle3 = findViewById(R.id.circle3);
        circle4 = findViewById(R.id.circle4);
        circle5 = findViewById(R.id.circle5);
        settingsButton = findViewById(R.id.settingsButton);

        // Запуск анимации
        animateCircles();
    }

    private void animateCircles() {
        // Список кругов для анимации
        CardView[] circles = {circle1, circle2, circle3, circle4, circle5};

        // Сдвигаем все круги за пределы экрана вверх
        for (CardView circle : circles) {
            circle.setTranslationY(-1000f); // Сдвиг вверх
            circle.setVisibility(View.VISIBLE); // Делаем видимыми перед анимацией
        }

        // Анимация для каждого круга с задержкой
        for (int i = 0; i < circles.length; i++) {
            final CardView circle = circles[i];
            int finalI = i;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Анимация с изгибом
                    TranslateAnimation animation = new TranslateAnimation(
                            0f, (finalI * 20f - 40f), // Небольшой горизонтальный сдвиг для изгиба
                            -1000f, 0f // Перемещение сверху вниз
                    );
                    animation.setDuration(500); // Длительность анимации
                    animation.setFillAfter(true); // Оставляем на конечной позиции
                    circle.startAnimation(animation);
                }
            }, i * 200); // Задержка между кругами (200 мс)
        }
    }
}