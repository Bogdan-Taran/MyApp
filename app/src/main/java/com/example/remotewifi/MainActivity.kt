package com.example.remotewifi

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.remotewifi.databinding.ActivityMainBinding
import okhttp3.OkHttpClient //добавляем
import okhttp3.Request
import okio.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var request: Request //добавляем переменную для запросов
    private lateinit var binding: ActivityMainBinding   //добавляем переменную для binding-класса
    private lateinit var pref: SharedPreferences
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   // инициализируем нашу переменную binding
        enableEdgeToEdge()
        setContentView(binding.root)    //устанавливаем binding на родительский элемент

        pref = getSharedPreferences( "MyPref", MODE_PRIVATE)
        OnClickSaveIp()
        getIP()

        binding.apply {
            lightOnOff.setOnClickListener(onClickListenner())
        }
    }

    private fun onClickListenner(): OnClickListener {
        return OnClickListener{
            when(it.id){
                R.id.light_on_off -> { post("led_1") }
            }

        }
    }

    private fun getIP() = with(binding){
        val ip = pref.getString("ip", "")
        if (ip != null){
            if (ip.isNotEmpty()) ipESP.setText(ip)
        }
    }

    private fun OnClickSaveIp() = with(binding){
        saveIpAdderss.setOnClickListener {
            if (ipESP.text.isNotEmpty()) saveIP(ipESP.text.toString())
        }
    }


    private fun saveIP(ip: String){
        val editor = pref.edit()
        editor.putString("ip", ip)
        editor.apply()
    }

    private fun post(post: String){ //led_1
        Thread{
            request = Request.Builder().url("http://${binding.ipESP.text}/$post").build()   //запрос на МК
            try {
                var response = client.newCall(request).execute()    //ответ от МК
                if(response.isSuccessful){
                    val resulText = response.body?.string()
                    runOnUiThread{
                        binding.tvTemp.text = resulText
                    }
                }

            } catch (i: IOException){

            }


        }.start()
    }


    }
