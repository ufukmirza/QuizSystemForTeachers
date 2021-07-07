package com.example.mobilprogramming;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class Login extends AppCompatActivity {
        Button button1,button2;
        TextView text;
        EditText Lemail,Lpassword1;
        boolean basarili;
        int attemtInfo=3;
        String girisİsim;

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button1=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        text=findViewById(R.id.textView);
        Lemail=findViewById(R.id.emailLogin);
        Lpassword1=findViewById(R.id.passwordLogin);
        basarili=false;
        button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        String email = Lemail.getText().toString();
                        String password1 = Lpassword1.getText().toString();
                        Gson gson = new Gson();
                        //val json = gson.toJson(persons) //tasks is an ArrayList instance variable
                        SharedPreferences appSharedPrefs =getApplicationContext().getSharedPreferences("users", MODE_PRIVATE);
                        String json = appSharedPrefs.getString("persons", "");
                        ArrayList<Person> persons  = gson.fromJson(json, new TypeToken<ArrayList<Person>>(){}.getType());
                        for( Person person: persons){
                                if(person.getEmail().equals(email)&&person.getPassword().equals(password1)){
                                        basarili=true;
                                        girisİsim=person.getName();
                                }
                        }

                        if(basarili==true){
                                Intent intent=new Intent(Login.this,MainActivity.class);
                                intent.putExtra("name",girisİsim);
                                startActivity(intent);

                        }
                        else{

                                attemtInfo--;
                                text.setText("Kalan giriş hakkı: "+attemtInfo);
                                if(attemtInfo==0){
                                        Toast.makeText(Login.this,"Giriş hakkı doldu uygulama kapatılıyor",Toast.LENGTH_SHORT).show();
                                        finish();
                                }
                        }

                }


        });

        button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intentReg=new Intent(Login.this,Register.class);
                        startActivity(intentReg);
                }
        });




        }
}
