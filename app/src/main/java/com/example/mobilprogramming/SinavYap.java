package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.core.content.FileProvider.getUriForFile;


public class SinavYap extends AppCompatActivity {

    private static final String FILENAME = "TextFileToSend.txt";
    private EditText puan,sure;
    private TextView zorluk;
    private Button kaydet;
    private Button mailAt;
    RecyclerView recyclerView;
    String emailBody="selaaam";
    private String stringFile = Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOCUMENTS.toString()+"/sinav.txt";
//   String stringFile = "data/data/com.example.mobilprogramming/files/sinav.txt";
    //String pathSinav="/data/data/com.example.mobilprogramming/files/sinav.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sinav_yap);
        puan=findViewById(R.id.editTextPuan);
        sure=findViewById(R.id.editTextSure);
        zorluk=findViewById(R.id.textViewZorluk);
        kaydet=findViewById(R.id.buttonKaydet);
        mailAt=findViewById(R.id.butonMail);
        recyclerView=findViewById(R.id.recyclerView2);

        Gson gson=new Gson();
        SharedPreferences appSharedPrefs =getApplicationContext().getSharedPreferences("ayar", MODE_PRIVATE);
        String json = appSharedPrefs.getString("sinavAyar", "");
        Sinav sinav = gson.fromJson(json,Sinav.class);


        DatabaseHelper dbhelper=new DatabaseHelper(SinavYap.this);
        ArrayList<Sorular> sorularim=dbhelper.readSpecific(Integer.valueOf(sinav.getZorlukDuzeyi()));


        //kontrol=findViewById(R.id.textView9);
        //kontrol.setText(sorularim.get(0).getCevap2()+" "+sorularim.get(0).getCevap3()+" "+sorularim.get(0).getCevap4());

        SinavAdapter sinavAdapter = new SinavAdapter(this, sorularim);
        recyclerView.setAdapter(sinavAdapter) ;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sinavAdapter.writeToFile(SinavYap.this,puan.getText().toString(),sure.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mailAt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    sinavAdapter.writeToFile(SinavYap.this,puan.getText().toString(),sure.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File file = new File("sinav.txt");
                if (!file.exists()){
                    Toast.makeText(SinavYap.this, "File doesn't exists", Toast.LENGTH_LONG).show();

                }
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("application/pdf");
                intentShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intentShare.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                //FileProvider.getUriForFile(null,null,file);
              //  intentShare.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(null,null,file));
                File txtPath = new File(SinavYap.this.getFilesDir(),"files");
                File newFile = new File(txtPath, "sinav.txt");
//                Uri contentUri = getUriForFile(SinavYap.this, "com.example.mobilprogramming", newFile);

              //  Uri contentUri = getUriForFile(SinavYap.this, "com.example.mobilprogramming", file);
                intentShare.putExtra(Intent.EXTRA_STREAM,  FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                        BuildConfig.APPLICATION_ID + ".provider", file));
                startActivity(Intent.createChooser(intentShare, "Share the file ..."));
          //      File file = new File(stringFile);




            }
        });
    }



    private void sendIntentToGmailApp(File fileToSend) {
        if(fileToSend != null){
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_SUBJECT, "Send Text File As Attachment Example");
            email.putExtra(Intent.EXTRA_TEXT, emailBody);
            email.putExtra(Intent.EXTRA_STREAM, Uri.parse("content:" + fileToSend.getAbsoluteFile()));
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email , "Send Text File"));

        }
    }


}