package com.example.remotewifi;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.hardware.ConsumerIrManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


public class ActivityRoomBogdanRGB extends AppCompatActivity {
    ConsumerIrManager IrMan;

    private static final int[] Up = {9300,4400, 650,500, 650,500, 600,500, 650,500, 700,450, 650,450, 700,450, 700,450, 700,1550, 700,1600, 650,1600, 700,1600, 650,500, 600,1650, 650,1600, 700,1600, 650,450, 700,450, 700,450, 650,500, 650,450, 700,450, 700,450, 700,450, 650,1600, 700,1600, 600,1650, 650,1600, 650,1650, 650,1600, 650,1600, 700,1600, 650};
    //Down
    private static final int[] Down = {9150,4450, 650,500, 700,450, 650,450, 650,500, 650,500, 600,550, 600,500, 650,500, 600,1650, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,1600, 700,450, 650,450, 650,500, 650,500, 600,550, 600,500, 650,500, 600,550, 600,1650, 600,1650, 650,1650, 600,1650, 600,1650, 650,1600, 650,1650, 600};
    //OFF
    private static final int[] Off = {9200,4450, 650,450, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 700,450, 650,1600, 650,1600, 700,1600, 650,1600, 650,500, 600,1650, 650,1600, 650,1600, 700,450, 700,1550, 700,450, 650,500, 650,450, 700,450, 650,500, 650,450, 650,1600, 700,450, 650,1600, 700,1550, 700,1600, 650,1600, 650,1600, 700,1600, 650};
    //ON
    private static final int[] ON = {9250,4400, 650,500, 650,500, 600,500, 650,500, 650,500, 650,450, 650,500, 700,450, 650,1600, 650,1600, 700,1600, 600,1650, 650,500, 650,1600, 650,1600, 700,1600, 600,1650, 650,1600, 700,450, 650,500, 650,450, 700,450, 650,500, 600,500, 700,450, 650,500, 650,1600, 700,1550, 650,1650, 650,1600, 650,1600, 700,1600, 600};
    //(1,4)
    private static final int[] Flash = {9300,4450, 700,450, 650,500, 600,500, 650,500, 650,500, 600,550, 600,550, 600,550, 600,1650, 600,1650, 650,1650, 600,1650, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,1600, 650,500, 650,1650, 600,500, 650,500, 650,500, 650,500, 650,500, 600,500, 650,1650, 600,500, 650,1650, 600,1650, 650,1650, 600,1650, 600};
    //(2,4)
    private static final int[] Strobe = {9200,4450, 600,550, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,1650, 650,1600, 650,1600, 700,1600, 600,500, 650,1600, 700,1550, 700,1600, 650,1600, 650,1600, 650,1600, 650,1650, 600,500, 700,450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 650,1600, 700,1600, 650,1600, 700,1550, 700};
    //(3,4)
    private static final int[] Fade = {9150,4450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 700,450, 650,500, 650,1600, 650,1600, 650,1600, 700,1550, 700,450, 650,1600, 700,1600, 650,1600, 700,1550, 700,1550, 700,450, 700,450, 650,1600, 650,500, 650,450, 700,450, 650,500, 650,450, 700,1600, 650,1600, 650,450, 700,1600, 650,1600, 650,1600, 650};
    //(4,4)
    private static final int[] Smooth = {9150,4500, 550,600, 600,500, 600,600, 500,600, 550,550, 600,600, 500,600, 550,550, 600,1700, 550,1700, 550,1700, 600,1650, 600,550, 550,1750, 500,1750, 550,1700, 550,1700, 600,1650, 600,1650, 600,600, 550,1700, 550,550, 550,550, 600,550, 600,600, 500,600, 550,600, 550,1700, 550,600, 500,1750, 500,1750, 550,1700, 550};
    //Color PWM's
    //Red
    private static final int[] Red = {9250,4450, 650,500, 650,500, 600,550, 600,550, 600,550, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 650,1600, 650,550, 600,1650, 650,1600, 650,1650, 600,500, 650,500, 650,1650, 600,500, 650,550, 600,500, 650,500, 650,500, 600,1650, 650,1650, 600,550, 600,1650, 650,1650, 600,1650, 650,1600, 650,1650, 600};
    //Green
    private static final int[] Green = {9250,4500, 600,500, 650,500, 600,550, 600,500, 650,500, 600,550, 650,500, 600,550, 600,1650, 650,1650, 550,1700, 600,1700, 600,500, 600,1700, 650,1600, 650,1650, 600,1650, 600,600, 550,1700, 600,550, 550,600, 550,550, 600,550, 550,600, 550,600, 550,1700, 600,550, 600,1650, 600,1700, 550,1750, 600,1650, 550,1750, 550};
    //Blue
    private static final int[] Blue = {9250,4450, 650,500, 650,500, 600,550, 600,500, 650,500, 650,500, 650,500, 650,500, 600,1650, 650,1650, 650,1600, 650,1600, 700,450, 650,1650, 650,1600, 700,1600, 650,450, 750,1550, 650,1600, 700,450, 700,450, 650,500, 650,500, 600,500, 700,1600, 650,450, 700,450, 700,1600, 650,1600, 700,1600, 650,1600, 650,1650, 600};
    //White
    private static final int[] White = {9200,4500, 600,550, 600,550, 600,500, 600,550, 600,550, 600,500, 600,550, 600,550, 600,1700, 600,1650, 600,1650, 600,1700, 600,500, 650,1650, 600,1650, 600,1650, 600,1700, 600,1650, 600,1700, 600,500, 650,550, 600,500, 600,550, 550,600, 600,500, 600,550, 600,550, 600,1650, 650,1650, 600,1650, 600,1700, 600,1650, 600};
    //(1,1)
    private static final int[] Orange = {9300,4400, 700,450, 700,450, 650,500, 650,500, 650,450, 700,450, 700,450, 650,500, 650,1600, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,450, 700,450, 700,450, 700,1600, 650,500, 650,450, 700,450, 700,450, 700,1600, 650,1600, 650,1650, 650,450, 700,1600, 650,1600, 700,1550, 700,1600, 650};
    //(1,2)
    private static final int[] Lgreen = {9250,4450, 650,500, 650,500, 600,550, 600,550, 600,500, 650,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,500, 600,1650, 650,1600, 650,1650, 600,1650, 650,500, 600,500, 650,1650, 600,500, 650,500, 650,500, 600,550, 600,500, 650,1650, 600,1650, 650,500, 600,1650, 650,1650, 600,1650, 600,1650, 650};
    //(1,3)
    private static final int[] Mblue = {9200,4450, 650,500, 700,450, 650,450, 700,450, 650,500, 650,450, 700,450, 700,450, 650,1600, 650,1600, 700,1550, 650,1600, 700,450, 650,1600, 650,1600, 700,1600, 650,450, 700,1600, 650,450, 650,1650, 650,450, 700,450, 650,500, 600,500, 650,1650, 650,450, 700,1550, 650,500, 650,1600, 700,1550, 700,1550, 700,1600, 650};
    //(2,1)
    private static final int[] MRed = {9250,4450, 650,500, 600,550, 600,500, 650,500, 600,550, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1600, 650,500, 650,1600, 650,1600, 650,1650, 600,500, 650,500, 650,1600, 650,1650, 600,500, 650,500, 650,500, 600,500, 650,1650, 600,1650, 600,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1650, 600};
    //(2,2)
    private static final int[] skyBlue = {9250,4450, 700,450, 650,500, 650,450, 700,450, 700,450, 650,450, 700,450, 700,450, 700,1550, 700,1550, 700,1600, 650,1600, 700,450, 650,1600, 700,1550, 700,1600, 650,1600, 700,450, 600,1650, 650,1600, 700,450, 650,500, 600,500, 650,500, 700,450, 650,1600, 700,450, 650,450, 700,1600, 650,1600, 650,1600, 700,1600, 650};
    //(2,3)
    private static final int[] Purple = {9200,4400, 600,550, 600,500, 650,500, 650,500, 600,550, 600,500, 650,500, 650,500, 600,1650, 600,1600, 650,1650, 650,1550, 650,500, 600,1650, 650,1600, 600,1650, 600,500, 650,1600, 650,1600, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 650,500, 700,450, 650,500, 600,1650, 600,1650, 700,1550, 700,1600, 600};
    //(3,1)
    private static final int[] LOrange = {9300,4350, 650,500, 650,450, 700,450, 700,450, 650,500, 650,450, 700,450, 650,500, 700,1550, 650,1600, 700,1600, 650,1600, 650,450, 700,1600, 650,1600, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 700,450, 650,500, 600,500, 700,1550, 700,1550, 700,1550, 700,1600, 650,450, 650,1650, 650,1600, 700,1550, 700};
    //(3,2)
    private static final int[] MMblue = {9200,4450, 650,500, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,1650, 600,1650, 650,1600, 650,1600, 650,500, 600,1650, 600,1650, 650,1600, 650,1650, 600,500, 650,500, 650,500, 600,1650, 650,500, 600,500, 650,500, 650,500, 600,1650, 650,1600, 650,1650, 600,500, 650,1600, 650,1650, 600,1650, 600};
    //(3,3)
    private static final int[] Mpurple = {9200,4400, 700,450, 650,500, 650,450, 650,500, 650,500, 650,450, 650,500, 650,450, 700,1550, 700,1600, 650,1600, 650,1600, 650,500, 650,1600, 650,1600, 650,1600, 700,450, 650,1600, 700,450, 650,450, 650,1650, 650,450, 700,450, 700,450, 650,1600, 650,450, 700,1550, 700,1600, 650,450, 700,1550, 700,1600, 600,1650, 650};
    //(4,1)
    private static final int[] Yellow = {9150,4500, 600,500, 650,500, 650,500, 600,500, 650,500, 650,500, 600,500, 650,500, 600,1650, 650,1600, 650,1600, 650,1650, 600,500, 650,1600, 650,1650, 600,1650, 600,550, 600,500, 650,1650, 600,500, 650,1600, 650,500, 600,550, 600,500, 650,1600, 650,1650, 600,500, 650,1600, 650,500, 650,1600, 650,1650, 600,1650, 600};
    //(4,2)
    private static final int[] SeaBlue = {9150,4500, 600,500, 650,500, 600,550, 600,500, 600,550, 600,550, 600,500, 600,550, 600,1650, 600,1650, 600,1650, 650,1600, 650,500, 650,1600, 650,1650, 600,1650, 600,1650, 600,550, 600,1650, 600,500, 650,1600, 650,500, 650,500, 600,500, 650,500, 650,1600, 650,500, 650,1600, 650,500, 600,1650, 600,1650, 600,1650, 650};
    //(4,3)
    private static final int[] Lpurple = {9300,4350, 600,550, 600,550, 600,550, 550,600, 550,550, 600,550, 600,500, 600,550, 600,1650, 600,1650, 600,1650, 600,1650, 600,550, 650,1650, 600,1650, 600,1650, 600,500, 650,1600, 650,1600, 650,500, 650,1600, 600,550, 650,500, 600,500, 650,1650, 600,500, 650,500, 600,1650, 600,550, 600,1650, 600,1650, 600,1650, 650};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_bogdan_rgb);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        IrMan = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);
        Button btnOnRGBLenta = findViewById(R.id.on_btn_on_pult_rgb);

        //Интенты

        ImageButton btn_backspace = (ImageButton) findViewById(R.id.btn_backspace);
        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(ActivityRoomBogdanRGB.this, ActivityMyRoomBogdan.class);
                startActivity(intent_back);finish();

            }
        });

        ImageButton btn_home = (ImageButton) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(ActivityRoomBogdanRGB.this, TheHomeActivity.class);
                startActivity(intent_home);finish();

            }
        });




        btnOnRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, ON);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        Button btnOffRGBLenta = findViewById(R.id.off_btn_on_pult_rgb);
        btnOffRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Off);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        Button btnUpRGBLenta = findViewById(R.id.up_btn_on_pult_rgb);
        btnUpRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Up);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        Button btnDownRGBLenta = findViewById(R.id.down_btn_on_pult_rgb);
        btnDownRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Down);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        Button btnFlashRGBLenta = findViewById(R.id.flash_btn_on_pult_rgb);
        btnFlashRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Flash);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        Button btnStrobeRGBLenta = findViewById(R.id.strobe_btn_on_pult_rgb);
        btnStrobeRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Strobe);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnFadeRGBLenta = findViewById(R.id.fade_btn_on_pult_rgb);
        btnFadeRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Fade);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnSmoothRGBLenta = findViewById(R.id.smooth_btn_on_pult_rgb);
        btnSmoothRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Smooth);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnRedRGBLenta = findViewById(R.id.red_btn_on_pult_rgb);
        btnRedRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Red);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnGreenRGBLenta =findViewById(R.id.green_btn_on_pult_rgb);
        btnGreenRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Green);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnBlueRGBLenta = findViewById(R.id.blue_btn_on_pult_rgb);
        btnBlueRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Blue);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnWhiteRGBLenta = findViewById(R.id.white_btn_on_pult_rgb);
        btnWhiteRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, White);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnOrangeRGBLenta =findViewById(R.id.orange_btn_on_pult_rgb);
        btnOrangeRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Orange);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnLgreenRGBLenta = findViewById(R.id.lgreen_btn_on_pult_rgb);
        btnLgreenRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Lgreen);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnMblueRGBLenta = findViewById(R.id.mblue_btn_on_pult_rgb);
        btnMblueRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Mblue);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnMredRGBLenta = findViewById(R.id.mred_btn_on_pult_rgb);
        btnMredRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, MRed);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnSkyblueRGBLenta= findViewById(R.id.skyblue_btn_on_pult_rgb);
        btnSkyblueRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, skyBlue);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnPurpleRGBLenta = findViewById(R.id.purple_btn_on_pult_rgb);
        btnPurpleRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Purple);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnLorangeRGBLenta = findViewById(R.id.lorange_btn_on_pult_rgb);
        btnLorangeRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, LOrange);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnMMblueRGBLenta = findViewById(R.id.mmblue_btn_on_pult_rgb);
        btnMMblueRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, MMblue);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnMpurpleRGBLenta = findViewById(R.id.mpurple_btn_on_pult_rgb);
        btnMpurpleRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Mpurple);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        Button btnYellowRGBLenta = findViewById(R.id.yellow_btn_on_pult_rgb);
        btnYellowRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Yellow);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        Button lpurple = findViewById(R.id. lpurple_btn_on_pult_rgb);
        lpurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, Lpurple);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


        Button btnSeaBlueRGBLenta = findViewById(R.id.seablue_btn_on_pult_rgb);
        btnSeaBlueRGBLenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IrMan.hasIrEmitter()){
                    IrMan.transmit(38000, SeaBlue);
                }else {
                    Toast.makeText(ActivityRoomBogdanRGB.this, "Эхх, у вас нет ИК передатчика", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });



    }
}