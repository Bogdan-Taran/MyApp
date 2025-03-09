// расстояние вьюшек на экране равно = 16 (расстояние top первой вьюшки) + 50 (высота 1 вьюшки) + 32 (расстояние до второй вьюшки снизу)

package com.example.remotewifi;



import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;


public class ActivityMyRoomBogdan extends AppCompatActivity {
    private SwitchCompat customSwitch;
    private TextView switchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_room_bogdan);

        // Скрываем status bar
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        // Инициализация элементов
        customSwitch = findViewById(R.id.customSwitch);
        switchText = findViewById(R.id.switchText);

        // Начальное состояние: текст справа
        switchText.setText("ВЫКЛ");
        switchText.setTranslationX(0f); // Начальная позиция (справа)

        // Слушатель для переключения
        customSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateSwitchPositionAndText(isChecked);
        });
    }

    public void toggleView(View view) {
        // Логика переключения между "Устройства" и "Пресеты"
        int currentPosition = switchText.getText().toString().equals("Устройства") ? 0 : 1;
        switchText.setText(currentPosition == 0 ? "Пресеты" : "Устройства");
    }

    private void updateSwitchPositionAndText(boolean isChecked) {
        String newText = isChecked ? "ВКЛ" : "ВЫКЛ";
        float targetX = isChecked ? -50f : 0f; // Сдвиг влево на 50dp для "ВКЛ", обратно для "ВЫКЛ"

        // Анимация перемещения
        TranslateAnimation animation = new TranslateAnimation(
                switchText.getTranslationX(), // Начальная позиция
                targetX, // Конечная позиция
                0f, // Начальная Y
                0f // Конечная Y
        );
        animation.setDuration(300); // Длительность анимации
        animation.setFillAfter(true); // Оставляем на конечной позиции

        // Обновляем текст и запускаем анимацию
        switchText.setText(newText);
        switchText.startAnimation(animation);

        // Обновляем окончательную позицию
        switchText.setTranslationX(targetX);
    }
}