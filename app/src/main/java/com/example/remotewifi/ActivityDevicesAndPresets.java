package com.example.remotewifi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class ActivityDevicesAndPresets extends AppCompatActivity {

    private ViewPager2 view_pager_devices_and_presets;
    private RadioGroup devises_presets_switch_group;
    private RadioButton devices_radio_button;
    private RadioButton presets_radio_button;
    private Button button_back_from_room_bogdan_to_main_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_and_presets);

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

        // Инициализация элементов
        view_pager_devices_and_presets = findViewById(R.id.view_pager_devices_and_presets);
        devises_presets_switch_group = findViewById(R.id.devises_presets_switch_group);
        devices_radio_button = findViewById(R.id.devices_radio_button);
        presets_radio_button = findViewById(R.id.presets_radio_button);
        button_back_from_room_bogdan_to_main_home = findViewById(R.id.button_back_from_room_bogdan_to_main_home);

        // Устанавливаем адаптер для ViewPager2
        FragmentAdapter adapter = new FragmentAdapter(this);
        view_pager_devices_and_presets.setAdapter(adapter);
        //view_pager_devices_and_presets.setPageTransformer(new ZoomOutPageTransformer());

        // Связываем ViewPager2 с RadioGroup
        devises_presets_switch_group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.devices_radio_button) {
                view_pager_devices_and_presets.setCurrentItem(0, true); // Переключаемся на первый фрагмент
            } else if (checkedId == R.id.presets_radio_button) {
                view_pager_devices_and_presets.setCurrentItem(1, true); // Переключаемся на второй фрагмент
            }
        });

        // Слушатель изменений страницы в ViewPager2
        view_pager_devices_and_presets.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) {
                    devices_radio_button.setChecked(true); // Выбираем первую радиокнопку
                } else {
                    presets_radio_button.setChecked(true); // Выбираем вторую радиокнопку
                }
            }
        });

        // Клик на кнопку "Назад"
        button_back_from_room_bogdan_to_main_home.setOnClickListener(v -> {
            Intent intent_back_from_room_bogdan_to_main_home = new Intent(ActivityDevicesAndPresets.this, TheHomeActivity.class);
            startActivity((intent_back_from_room_bogdan_to_main_home));
            finish(); // Закрываем активность
        });
    }
}