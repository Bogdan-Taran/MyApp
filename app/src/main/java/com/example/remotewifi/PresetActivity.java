package com.example.remotewifi;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PresetActivity extends Fragment {

    ImageView backgroundImage;
    Button btn_turn_on_off_all_light;
    Button btn_turn_on_off_all_podsvetka_stol;
    Button btn_end_of_the_film;
    Button btn_turn_everything_off;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_preset, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        backgroundImage = view.findViewById(R.id.img_background_for_activity_preset);

//        btn_turn_on_off_all_light = view.findViewById(R.id.btn_turn_on_off_all_light);
//
//        btn_turn_on_off_all_podsvetka_stol = view.findViewById(R.id.btn_turn_on_off_all_podsvetka_stol);
//
//        btn_end_of_the_film = view.findViewById(R.id.btn_end_of_the_film);
//        btn_turn_everything_off = view.findViewById(R.id.btn_turn_everything_off);

        // Загрузка изображения через Glide
        Glide.with(this)
                .load(R.drawable.img_background_for_activity_preset) // Укажите нужное изображение
                .transform(new BlurTransformation(25)) // Размытие с радиусом 25
                .into(backgroundImage);


    };


}