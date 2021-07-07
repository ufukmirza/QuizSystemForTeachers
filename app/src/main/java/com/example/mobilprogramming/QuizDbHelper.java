package com.example.mobilprogramming;

import android.content.ContentValues;
import android.content.Context;
import com.example.mobilprogramming.QuizContract.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Sorularim.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
               QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " TEXT" +
                QuestionsTable.COLUMN_ZORLUK+"TEXT"+
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    public void fillQuestionsTable() {
        Sorular q1 = new Sorular("A is correct", "A", "B", "C","D", "a","1");
        addQuestion(q1);
        /*
        Sorular q2 = new Sorular("B is correct", "A", "B", "C","D" ,"b","2");
        addQuestion(q2);
        Sorular q3 = new Sorular("C is correct", "A", "B", "C", "D","c","3");
        addQuestion(q3);
        Sorular q4 = new Sorular("A is correct again", "A", "B", "C","D", "a","4");
        addQuestion(q4);
        Sorular q5 = new Sorular("B is correct again", "A", "B", "C", "D","b","5");
        addQuestion(q5);

         */
    }
    public void addQuestion(Sorular question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getSoru());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getCevap1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getCevap2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getCevap3());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getCevap4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getDogru());
        cv.put(QuestionsTable.COLUMN_ZORLUK,question.getZorluk());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
}