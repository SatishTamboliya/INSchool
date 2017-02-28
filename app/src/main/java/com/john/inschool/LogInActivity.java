package com.john.inschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by john on 26/2/17.
 */

public class LogInActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern =Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private EditText editText1;
    private EditText editText2;
    private ImageButton button;
    private ImageView imageView;
    private LinearLayout linearLayout;
    private TextView textView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    TextInputLayout usernameWrapper;
    TextInputLayout passwordWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        editText1 = (EditText) findViewById(R.id.Email_input);
        editText2 = (EditText) findViewById(R.id.Password_input);
        button = (ImageButton) findViewById(R.id.Login_Button);
        imageView = (ImageView) findViewById(R.id.INSchool_logo);
        linearLayout = (LinearLayout) findViewById(R.id.activity_login);
        textView = (TextView) findViewById(R.id.signUpTextView);
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();;

        mAuth = FirebaseAuth.getInstance();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                hideKeyBoard();
                String username = usernameWrapper.getEditText().getText().toString();
                String password = passwordWrapper.getEditText().getText().toString();
                usernameWrapper.setError(null);
                passwordWrapper.setError(null);
                if (!validateEmail(username)) {
                    usernameWrapper.setError("Not a valid email address!");
                }
                if (!validatePassword(password)) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);

                    mAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Log.w(TAG, "signInWithEmail", task.getException());
                                        Toast.makeText(LogInActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }else{
                                        if(!firebaseUser.isEmailVerified()){

                                            Toast.makeText(LogInActivity.this, "Please verify your email before login",
                                                    Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(LogInActivity.this, "Welcome to profile",
                                                    Toast.LENGTH_SHORT).show();

                                            //make welcome page and navigate there
                                        }
                                    }

                                    // ...
                                }
                            });
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make sign up page and navigate there
                startActivity(new Intent(LogInActivity.this , SignUpActivity.class));
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    public boolean validateEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password){
        return password.length() >4;
    }

    public void hideKeyBoard(){
        View view = getCurrentFocus();
        if(view != null){
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
