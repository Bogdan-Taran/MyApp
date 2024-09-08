package com.example.remotewifi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class RegistrationActivity extends AppCompatActivity {

    private EditText reg_username, reg_email, reg_password;
    private DatabaseReference DataBasePerPsw;   // создаём объект который будет свмещать в себя ссылку на базу данных
    private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialization_of_variables();
    }

    public void initialization_of_variables(){
        reg_username = findViewById(R.id.reg_username);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);

        DataBasePerPsw = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    public void onClickReg(View view){
        String id = DataBasePerPsw.getKey();
        String username = reg_username.getText().toString();
        String email = reg_email.getText().toString();
        String password = reg_password.getText().toString();

        NewUser nUser = new NewUser(id, username, email, password);
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            DataBasePerPsw.push().setValue(nUser);
            Toast.makeText(this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
            Intent intent_to_signIn = new Intent(RegistrationActivity.this, AutorizationActivity.class);
            startActivity(intent_to_signIn);finish();
        }
        else {
            Toast.makeText(this, "Вы что-то забыли вписать", Toast.LENGTH_SHORT).show();
        }






    }





}








