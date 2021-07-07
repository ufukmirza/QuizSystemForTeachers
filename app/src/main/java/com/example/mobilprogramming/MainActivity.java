package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageButton eEkle,eListele,eSinav,eAyar;
    private TextView isim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        eEkle=findViewById(R.id.imageButtonAddQuestion);
        eListele=findViewById(R.id.imageButtonList);
        eSinav=findViewById(R.id.imageButtonMake);
        eAyar=findViewById(R.id.imageButtonSettings);

*/
        isim=findViewById(R.id.textViewMainUser);
        Intent intent=getIntent();
        String name = intent.getStringExtra("name");
        isim.setText(name);


    }
    public void onClick(View v) {

        switch(v.getId()){

            case  R.id.imageButtonAddQuestion:
                Intent intentEkle = new Intent(this, SoruEkle.class);
                startActivity(intentEkle);
                break;

            case R.id.imageButtonList:
                Intent intentListele = new Intent(this, Listele.class);
                startActivity(intentListele);
                break;
            case  R.id.imageButtonMake:
                Intent intentMake = new Intent(this, SinavYap.class);
                startActivity(intentMake);
                break;

            case R.id.imageButtonSettings:
                Intent intentSet = new Intent(this, Ayarlar.class);
                 startActivity(intentSet);
                break;
        }

    }
}