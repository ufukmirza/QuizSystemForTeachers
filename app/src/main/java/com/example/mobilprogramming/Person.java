package com.example.mobilprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;
    private String surname;
    private String email;
    private String birtday;
    private String phoneNumber;
    private String password;
    //private int photoId;

    public Person(String name, String surname, String email, String phoneNumber, String password/*,/int photoId*/) {
        this.name = name;this.surname = surname;this.email = email;
        this.phoneNumber = phoneNumber; this.password = password; /*this.photoId=photoId;*/
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password;  }



/*
    public int getPhotoId() { return photoId; }
    public void setPhotoId(int photoId) { this.photoId = photoId; }*/

}
