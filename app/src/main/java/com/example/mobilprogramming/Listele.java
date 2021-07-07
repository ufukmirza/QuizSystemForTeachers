package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Listele extends AppCompatActivity {
    ArrayList<Sorular> sorularim;
    RecyclerView recyclerView;
    TextView kontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listele);
        DatabaseHelper dbhelper=new DatabaseHelper(Listele.this);
        ArrayList<Sorular> sorularim=dbhelper.readData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //kontrol=findViewById(R.id.textView9);

      //  kontrol.setText(sorularim.get(0).getCevap2()+" "+sorularim.get(0).getCevap3()+" "+sorularim.get(0).getCevap4());
        SorularAdapter sorularAdapter = new SorularAdapter(this, sorularim);
        recyclerView.setAdapter(sorularAdapter) ;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}