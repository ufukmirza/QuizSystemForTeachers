package com.example.mobilprogramming;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Bundle;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class SorularAdapter extends RecyclerView.Adapter<SorularAdapter.MyViewHolder>  {

    ArrayList<Sorular> sorularim;
    LayoutInflater inflater;

    public SorularAdapter(Context context, ArrayList<Sorular> sorularim) {
        inflater = LayoutInflater.from(context);
        this.sorularim = sorularim;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.soru_karti, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sorular seciliSoru = sorularim.get(position);
        holder.setData(seciliSoru, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogMaker dm=new DialogMaker();
                dm.showAlertDialog(view.getContext(),sorularim.get(position),inflater);
               // Dialog2 dFragment = new Dialog2().newInstance(sorularim.get(position));
                //dFragment.show(getSupportFragmentManager(), "Frag");


                Log.d(TAG, "onClick: closing dialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return sorularim.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView question,option1,option2,option3,option4,answer,zor;


        public MyViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.textView10);
            option1 = (TextView) itemView.findViewById(R.id.textView11);
            option2 = (TextView) itemView.findViewById(R.id.textView12);
            option3 = (TextView) itemView.findViewById(R.id.textView13);
            option4 = (TextView) itemView.findViewById(R.id.textView14);
            answer = (TextView) itemView.findViewById(R.id.textView15);
            zor=(TextView) itemView.findViewById(R.id.textView16);






        }

        public void setData(Sorular seciliSoru, int position) {

            this.question.setText(seciliSoru.getSoru());
            this.option1.setText(seciliSoru.getCevap1());
            this.option2.setText(seciliSoru.getCevap2());
            this.option3.setText(seciliSoru.getCevap3());
            this.option4.setText(seciliSoru.getCevap4());
            this.answer.setText(seciliSoru.getDogru());
            this.zor.setText(seciliSoru.getZorluk());

        }


        @Override
        public void onClick(View v)  {

            }


        }


}

