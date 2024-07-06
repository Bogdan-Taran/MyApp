package com.example.remotewifi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.remotewifi.databinding.ActivityMainBinding;
import com.example.remotewifi.databinding.ActivityTheHomeBinding;

public class TheHomeActivity extends AppCompatActivity {
    SharedPreferences pref;
    private EditText  edText;
    private ImageView icon_save_ip;
    private static final String toastShowText = "IP сохранён";
    private Button btn_room_bogdan_intent;


    private TextLinks.Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_the_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_room_bogdan_intent = (Button) findViewById(R. id. room_bogdan);
        edText = (EditText) findViewById(R.id.edit_text_save_ip);
        icon_save_ip = (ImageView) findViewById(R.id.image_save_ip);
        pref = getSharedPreferences("Abrakadabra", MODE_PRIVATE);
        OnClickSaveIp();
        getIp();
        intentToRoomBogdan();
    }

        private void getIp(){
            String ip = pref.getString("ip", "");
            if(ip != null) {
                if(!ip.isEmpty()){
                    edText.setText(ip);
                }
            }
        }

        private void saveIp(String ip){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("ip", ip);
            editor.apply();
        }

        private void OnClickSaveIp(){
            icon_save_ip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!edText.getText().toString().isEmpty()) {
                        saveIp(edText.getText().toString());
                    };
                    toastClickShow();

                }
            });
        }
        private void toastClickShow(){
            Toast.makeText(this, toastShowText, Toast.LENGTH_SHORT).show();
        }

        private void intentToRoomBogdan(){
        btn_room_bogdan_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRoomBogdan = new Intent(TheHomeActivity.this, ActivityMyRoomBogdan.class);
                startActivity(toRoomBogdan);

            }
        });
        }







}
