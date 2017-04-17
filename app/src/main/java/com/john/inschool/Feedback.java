package com.john.inschool;

/**
 * Created by john on 17/4/17.
 */

public class Feedback {
       public String name;
    public String email;
    public String phone;
    public String message;
    public Feedback(){

    }

    public Feedback(String name, String email, String phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }
    @Override
    public String toString(){
        return name+email+phone+message;
    }
}
