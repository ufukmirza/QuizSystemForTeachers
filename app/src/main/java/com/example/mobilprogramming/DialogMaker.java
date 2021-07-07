package com.example.mobilprogramming;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.io.Serializable;

import static java.security.AccessController.getContext;


public class DialogMaker extends Fragment{
    Button update;
    Button delete;
    Activity listeleActivity;
    LayoutInflater inflater;
    private static final String TAG = "MyCustomDialog";
    private TextView mActionOk, mActionCancel;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_dialog, container, false);
        update = view.findViewById(R.id.button5);
        delete = view.findViewById(R.id.button6);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                //get().dismiss();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");
               // getDialog().dismiss();
            }
        });

        return view;
    }

        public void showAlertDialog(Context context,Sorular soru,LayoutInflater inflater2) {
        inflater=inflater2;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("AlertDialog");
        String[] items = {"sil","Güncelle"};
        int checkedItem = 1;
        Log.d(TAG, "onClick: closing dialog");
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//dialog.cancel();
                Log.d("null",items[which]);

                if(items[which].equals("sil")){
/*
                    DatabaseHelper dbhelper=new DatabaseHelper(context);
                    dbhelper.deleteRow(soru.getSoru(),soru.getCevap1(),soru.getDogru());

                    Intent intent=new Intent(inflater.getContext(),Listele.class);
                    inflater.getContext().startActivity(intent);
                    */ dialog.cancel();

                    DialogMaker2 dm=new DialogMaker2();
                    dm.showAlertDialog(inflater.getContext(),soru,inflater);

                }

                if(items[which].equals("Güncelle")){
                    DatabaseHelper dbhelper=new DatabaseHelper(context);
                    dbhelper.deleteRow(soru.getSoru(),soru.getCevap1(),soru.getDogru());
//Toast.makeText(context,"sa",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(inflater.getContext(),SoruEkle.class);
                    intent.putExtra("soru",soru);
                    intent.putExtra("guncelleme_mi",true);

                    inflater.getContext().startActivity(intent);

                }

//dialog.cancel();
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }
    /*
    public void updateDetail() {

        Intent intent=new Intent(inflater.getContext(),SoruEkle.class);
        intent.putExtra("soru",);
        inflater.getContext().startActivity(intent);

    }
    */

}
