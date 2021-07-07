package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SoruEkle extends AppCompatActivity {

    private Spinner espinner;
    private RadioButton ra,rb,rc,rd;
    private EditText eQuestion,aCevap,bCevap,cCevap,dCevap;
    private Button eEkle;
    private RadioGroup erG;
    private String dogru;
    private DatabaseHelper dbhelper;
    TextView kontrol;
    private Boolean guncelleme_mi=false;
    private Sorular soru;

    //private QuizDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soru_ekle);
        espinner=findViewById(R.id.spinner);
        eQuestion=findViewById(R.id.editTextQuestion);
        eEkle=findViewById(R.id.button3Ekle);
        erG=findViewById(R.id.rg);
        ra=findViewById(R.id.radioButton);
        rb=findViewById(R.id.radioButton2);
        rc=findViewById(R.id.radioButton3);
        rd=findViewById(R.id.radioButton4);
        aCevap=findViewById(R.id.editTextA);
        bCevap=findViewById(R.id.editTextB);
        cCevap=findViewById(R.id.editTextC);
        dCevap=findViewById(R.id.editTextD);
        kontrol=findViewById(R.id.textView4);
        Intent intent=getIntent();
        if(intent!=null) {
            soru = (Sorular) getIntent().getSerializableExtra("soru");
            guncelleme_mi = getIntent().getBooleanExtra("guncelleme_mi", false);

        }
        ArrayList<Integer>zorluk=new ArrayList<>();
        for(int i=1;i<=5;i++){
            zorluk.add(i);
        }
        if(espinner!=null){
            ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,zorluk);
            espinner.setAdapter(adapter);
            espinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String secilenZorluk=espinner.getSelectedItem().toString();

                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                    return;
                }
            });
        }
        eEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question=eQuestion.getText().toString();
                String a=aCevap.getText().toString();
                String b=bCevap.getText().toString();
                String c=cCevap.getText().toString();
                String d=dCevap.getText().toString();
                if(ra.isChecked())
                    dogru= ra.getText().toString();
                else if(rb.isChecked())
                    dogru=rb.getText().toString();
                else if(rc.isChecked())
                    dogru=rc.getText().toString();
                else if(rd.isChecked())
                    dogru=rd.getText().toString();
                Sorular soru=new Sorular(question,a,b,c,d,dogru,espinner.getSelectedItem().toString());
                DatabaseHelper dbhelper=new DatabaseHelper(SoruEkle.this);
                dbhelper.instertData(soru);
                Toast.makeText(SoruEkle.this,"Başarılı kayıt ",Toast.LENGTH_SHORT).show();
                ArrayList<Sorular> sorular=dbhelper.readData();
                //kontrol.setText(sorular.get(3).getSoru());
                Intent intent1=new Intent(SoruEkle.this,MainActivity.class);
                startActivity(intent1);
                }
        });
        if(guncelleme_mi==true){
            eQuestion.setText(soru.getSoru());
            aCevap.setText(soru.getCevap1());
            bCevap.setText(soru.getCevap2());
            cCevap.setText(soru.getCevap3());
            dCevap.setText(soru.getCevap4());
            if(soru.getDogru().equals("a"))
                ra.setChecked(true);
            if(soru.getDogru().equals("b"))
                rb.setChecked(true);
            if(soru.getDogru().equals("c"))
                rc.setChecked(true);
            if(soru.getDogru().equals("d"))
                rd.setChecked(true);
            espinner.setSelection(Integer.parseInt(soru.getZorluk()));

        }

    }
}