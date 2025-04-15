// расстояние вьюшек на экране равно = 16 (расстояние top первой вьюшки) + 50 (высота 1 вьюшки) + 32 (расстояние до второй вьюшки снизу)

package com.example.remotewifi;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.remotewifi.SwitchCustomWithText;

import jp.wasabeef.glide.transformations.BlurTransformation;



public class ActivityMyRoomBogdan extends Fragment {

    private SwitchCustomWithText customSwitch;

    ImageView backgroundImage;


    @Nullable
    @Override
   public View onCreateView(@NonNull LayoutInflater inflater,
                            @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_my_room_bogdan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        Button button_back_from_room_bogdan_to_main_home = view.findViewById(R.id.button_back_from_room_bogdan_to_main_home);


        // Инициализация элементов
        //customSwitch = findViewById(R.id.switchButton);
        backgroundImage = view.findViewById(R.id.img_background_for_activity_preset);

        // Загрузка изображения через Glide
        Glide.with(this)
                .load(R.drawable.background_for_room_bogdan_devices) // Укажите нужное изображение
                .transform(new BlurTransformation(25)) // Размытие с радиусом 25
                .into(backgroundImage);

    }


}