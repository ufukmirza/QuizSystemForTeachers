package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class Ayarlar extends AppCompatActivity {
    private EditText sinavSure;
    private EditText sinavPuan;
    private EditText sinavZorluk;
    private Button ayarla;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        sinavSure=findViewById(R.id.editTextTextPersonName);
        sinavPuan=findViewById(R.id.editTextTextPersonName2);
        sinavZorluk=findViewById(R.id.editTextTextPersonName3);
        ayarla=findViewById(R.id.button3);
        ayarla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sinav sinav = new Sinav(Integer.valueOf(sinavSure.getText().toString())
                                        ,Integer.valueOf(sinavPuan.getText().toString())
                                        ,Integer.valueOf(sinavZorluk.getText().toString()));

                Gson gson =new  Gson();
                appSharedPrefs =getApplicationContext().getSharedPreferences("ayar", MODE_PRIVATE);
                prefsEditor=appSharedPrefs.edit();
                String json_2 = gson.toJson(sinav);//tasks is an ArrayList instance variable
                prefsEditor.putString("sinavAyar", json_2);
                prefsEditor.commit();
                Toast.makeText(Ayarlar.this,"Sınav ayarları kaydedildi",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Ayarlar.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}