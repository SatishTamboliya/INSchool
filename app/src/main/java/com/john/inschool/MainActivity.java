package com.john.inschool;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

private static final String TAG = "MainActivity";
    private EditText editText1;
    private EditText editText2;
    private ImageButton button;
    private ImageView imageView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toast.makeText(MainActivity.this, "In onCreate.",
                Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);


        editText1 = (EditText) findViewById(R.id.Email_input);
        editText2 = (EditText) findViewById(R.id.Password_input);
        button = (ImageButton) findViewById(R.id.Login_Button);
        imageView = (ImageView) findViewById(R.id.INSchool_logo);
        //imageView.setImageResource(R.drawable.INSchool_logo);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull  FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(MainActivity.this,"Logged in", Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Toast.makeText(MainActivity.this,"Logged off", Toast.LENGTH_SHORT).show();


                }
                // ...
            }
        };


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(editText1.getText().toString().trim(), editText2.getText().toString().trim())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(MainActivity.this, "signInWithEmail: successful",
                                            Toast.LENGTH_SHORT).show();

                                // ...
                            }
                        });
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        Toast.makeText(MainActivity.this, "In onStart.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
            Toast.makeText(MainActivity.this, "in OnStop.",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
