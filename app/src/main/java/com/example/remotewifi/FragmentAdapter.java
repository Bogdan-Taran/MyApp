package com.example.remotewifi;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Возвращаем нужный фрагмент в зависимости от позиции
        if (position == 0) {
            return new ActivityMyRoomBogdan();
        } else {
            return new PresetActivity();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Количество фрагментов
    }
}