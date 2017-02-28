package com.john.inschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    if (!user.isEmailVerified()){
                        //user.sendEmailVerification();
                        Toast.makeText(MainActivity.this , "please verify your Email address and try to login again." , Toast.LENGTH_SHORT);
                    }

                    else{
                        //make user profile and navigate there
                        Toast.makeText(MainActivity.this, "going to welcome page", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this, "going to LogInActivity", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this ,LogInActivity.class));
                }
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}