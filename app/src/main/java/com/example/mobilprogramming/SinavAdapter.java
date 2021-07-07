package com.example.mobilprogramming;

import android.content.Context;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SinavAdapter extends RecyclerView.Adapter<SinavAdapter.MyViewHolder>  {
    Integer row_index;
    ArrayList<Sorular> sorularim;
    LayoutInflater inflater;
    ArrayList<Sorular>sinavSorulari = new ArrayList<Sorular>();


    public SinavAdapter(Context context, ArrayList<Sorular> sorularim) {
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


                Log.d(null, "onClick: closing dialog");
            }
        });

    }
    @Override
    public int getItemCount() {
        return sorularim.size();
    }

    public String writeToFile(Context context,String puan,String sure) throws IOException {
         String root = "//"+Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOCUMENTS.toString();
        File myDir = new File(root,"sinav.txt");

        FileWriter writer = new FileWriter(myDir);
        try {

          //  OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("sinav.txt",MODE_PRIVATE));
            writer.write("Soru Puanı "+puan+"\n");
            writer.write("Sınav Süresi "+sure+"\n");
            writer.write("***********------------************------"+"\n");
            for(int i=0;i<sinavSorulari.size();i++){
                writer.write("Soru "+(i+1)+":"+sinavSorulari.get(i).getSoru()+"\n");
                writer.write("A) "+sinavSorulari.get(i).getCevap1()+"\n");
                writer.write("B) "+sinavSorulari.get(i).getCevap2()+"\n");
                writer.write("C) "+sinavSorulari.get(i).getCevap3()+"\n");
                writer.write("D) "+sinavSorulari.get(i).getCevap4()+"\n");
                writer.write("Cevap: "+sinavSorulari.get(i).getDogru()+"\n");
                writer.write("Zorluk düzeyi "+sinavSorulari.get(i).getZorluk()+"\n\n");

            }
            writer.close();
            Toast.makeText(inflater.getContext(),"Sınav .txt olarak dosyalara kaydedildi.",Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

        return myDir.getPath();

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView question,option1,option2,option3,option4,answer,zor;
        CardView soruKarti;
        LinearLayout linearCard;

        public MyViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.textView10);
            option1 = (TextView) itemView.findViewById(R.id.textView11);
            option2 = (TextView) itemView.findViewById(R.id.textView12);
            option3 = (TextView) itemView.findViewById(R.id.textView13);
            option4 = (TextView) itemView.findViewById(R.id.textView14);
            answer = (TextView) itemView.findViewById(R.id.textView15);
            zor=(TextView) itemView.findViewById(R.id.textView16);
            soruKarti=(CardView)itemView.findViewById(R.id.soruKartiView);
            linearCard=(LinearLayout)itemView.findViewById(R.id.cardLayout);

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
            // soruKarti.setBackgroundColor(Color.GREEN);
            // linearCard.setBackgroundColor(Color.GREEN);


        }


    }


}


