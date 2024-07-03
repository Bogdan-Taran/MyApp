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

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TheHomeActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences pref;
    EditText  edText;
    ImageView icon_save_ip;
    Button btnVeranda;

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
        pref = getSharedPreferences("MyPref", MODE_PRIVATE);

        edText = (EditText) findViewById(R.id.edit_text_save_ip);

        btnVeranda = (Button) findViewById(R.id.veranda);
        btnVeranda.setOnClickListener(this);

    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnVeranda:


        }


    }
}
