package com.example.mobilprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
String tablename="Sorularim";

    public DatabaseHelper(Context context) {
        super(context,"quiz_questions" ,null ,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create=" CREATE TABLE "+ tablename+"(id INTEGER PRIMARY KEY AUTOINCREMENT,soru VARCHAR(256),cevap1 VARCHAR(256),cevap2 VARCHAR(256)," +
                "cevap3 VARCHAR(256),cevap4 VARCHAR(256),dogru VARCHAR(256),zorluk VARCHAR(256))";
       sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void instertData(Sorular sorular){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cd=new ContentValues();
        cd.put("soru",sorular.getSoru());
        cd.put("cevap1",sorular.getCevap1());
        cd.put("cevap2",sorular.getCevap2());
        cd.put("cevap3",sorular.getCevap3());
        cd.put("cevap4",sorular.getCevap4());
        cd.put("dogru",sorular.getDogru());
        cd.put("zorluk",sorular.getZorluk());
        db.insert("Sorularim",null,cd);
    }
    public ArrayList<Sorular>readData(){


        ArrayList<Sorular> sorular= new ArrayList<Sorular>();
        SQLiteDatabase db=this.getWritableDatabase();
        String  query="Select * from Sorularim";
        Cursor result=db.rawQuery(query,null);
        if(result.moveToFirst()){

            do{

                Sorular soru=new Sorular("0","0","0","0","0","0","0") ;
                soru.setSoru(result.getString(result.getColumnIndex("soru")));
                soru.setCevap1(result.getString(result.getColumnIndex("cevap1")));
                soru.setCevap2(result.getString(result.getColumnIndex("cevap2")));
                soru.setCevap3(result.getString(result.getColumnIndex("cevap3")));
                soru.setCevap4(result.getString(result.getColumnIndex("cevap4")));
                soru.setDogru(result.getString(result.getColumnIndex("dogru")));
                soru.setZorluk(result.getString(result.getColumnIndex("zorluk")));

                sorular.add(soru);


            }while(result.moveToNext());

        }
        result.close();
        db.close();
        return sorular;

    }
     public void deleteRow(String soru,String cevap1,String dogru){
        SQLiteDatabase db=this.getWritableDatabase();
        // db.execSQL("delete from "+ tablename);
        //  db.delete(tablename,"soru=?",new String[]{"soru"});
        db.execSQL("DELETE FROM " + tablename+ " WHERE soru"+"='"+soru+"'"+"and cevap1='"+cevap1+"'"+"and dogru='"+dogru+"'");
        Log.d(null,"silindi");
    }
    public ArrayList<Sorular> readSpecific(int zorluk){

        ArrayList<Sorular> sorular= new ArrayList<Sorular>();
        SQLiteDatabase db=this.getWritableDatabase();
        String  query="Select * from Sorularim where zorluk='"+zorluk+"'";
        Cursor result=db.rawQuery(query,null);
        if(result.moveToFirst()){

            do{

                Sorular soru=new Sorular("0","0","0","0","0","0","0") ;
                soru.setSoru(result.getString(result.getColumnIndex("soru")));
                soru.setCevap1(result.getString(result.getColumnIndex("cevap1")));
                soru.setCevap2(result.getString(result.getColumnIndex("cevap2")));
                soru.setCevap3(result.getString(result.getColumnIndex("cevap3")));
                soru.setCevap4(result.getString(result.getColumnIndex("cevap4")));
                soru.setDogru(result.getString(result.getColumnIndex("dogru")));
                soru.setZorluk(result.getString(result.getColumnIndex("zorluk")));

                sorular.add(soru);


            }while(result.moveToNext());

        }
        result.close();
        db.close();
        return sorular;
    }

}
