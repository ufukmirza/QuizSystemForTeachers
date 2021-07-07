package com.example.mobilprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;
import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private EditText eRegName,eRegSurname,eRegMail,eRegPhone,eRegPass,eRegConPass;
    private Button eRegister;
    private TextView kontrol;
    SharedPreferences appSharedPrefs;
    SharedPreferences.Editor prefsEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        eRegName=findViewById(R.id.editTextPersonName);
        eRegSurname=findViewById(R.id.editTextPersonSurname);
        eRegMail=findViewById(R.id.editTextEmailAddress);
        eRegPhone=findViewById(R.id.editTextPhone);
        eRegPass=findViewById(R.id.editTextSignUpPassword);
        eRegConPass=findViewById(R.id.editTextSingUpPassword2);
        eRegister=findViewById(R.id.button);
        kontrol=findViewById(R.id.textView2);


        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regName = eRegName.getText().toString();
                String regSurname = eRegSurname.getText().toString();
                String regMail=eRegMail.getText().toString();
                String regPhone=eRegPhone.getText().toString();
                String regPass = eRegPass.getText().toString();
                String regConPass=eRegConPass.getText().toString();

                if(validate(regName,regSurname,regMail,regPhone,regPass,regConPass)){

                    Person person = new Person(regName, regSurname, regMail, regPhone,regPass);
                    ArrayList<Person> persons;
                    Gson gson =new  Gson();

                    appSharedPrefs =getApplicationContext().getSharedPreferences("users", MODE_PRIVATE);
                    prefsEditor=appSharedPrefs.edit();

                    String json = appSharedPrefs.getString("persons", "");
                    persons  = gson.fromJson(json, new TypeToken<ArrayList<Person>>(){}.getType());
                    if(persons==null) {
                        //String json = gson.toJson(persons); //ilkcalisma
                        persons=new ArrayList<Person>();
                        persons.add(person);
                    }
                    if(persons!=null){

                    persons.add(person);
                    }

                    String json_2 = gson.toJson(persons);//tasks is an ArrayList instance variable


                    prefsEditor.putString("persons", json_2);
                    prefsEditor.commit();



                    startActivity(new Intent(Register.this, Login.class));
                    Toast.makeText(Register.this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validate(String name,String surname,String mail,String phone, String password,String conpass){

        if(name.isEmpty() ||surname.isEmpty()||mail.isEmpty()||phone.isEmpty() ||password.length() < 1){
            Toast.makeText(this, "Bütün alanları doldurunuz!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.equals(conpass)==false){
            Toast.makeText(this,"Parolalar eşleşmiyor",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(true) {
            Gson gson = new Gson();
            //val json = gson.toJson(persons) //tasks is an ArrayList instance variable
            SharedPreferences appSharedPrefs = getSharedPreferences("users", MODE_PRIVATE);
            String json = appSharedPrefs.getString("persons", "");
            ArrayList<Person> persons;
            persons = gson.fromJson(json, new TypeToken<ArrayList<Person>>() {
            }.getType());

            //kontrol.append(json);
            if (persons != null){
                for (Person person : persons) {
                    if (person.getEmail().equals(mail)) {
                        Toast.makeText(this, "Bu isim zaten kayıtlı", Toast.LENGTH_SHORT).show();
                        return false;


                    }
                }
        }
        }
        return true;
    }
}
