package com.john.inschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        usernameWrapper = (TextInputLayout) findViewById(R.id.usernameWrapper);
        passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        editText1 = (EditText) findViewById(R.id.Email_input);
        editText2 = (EditText) findViewById(R.id.Password_input);
        button = (ImageButton) findViewById(R.id.SignUp_Button);
        imageView = (ImageView) findViewById(R.id.INSchool_logo);
        linearLayout = (LinearLayout) findViewById(R.id.activity_signUp);
        textView = (TextView) findViewById(R.id.signUpTextView);

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

                    mAuth.createUserWithEmailAndPassword(username , password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                if (task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(SignUpActivity.this , "Email id already registered!" , Toast.LENGTH_SHORT).show();
                                    Toast.makeText(SignUpActivity.this , "please try again with new Email id." , Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(SignUpActivity.this , "please try again" , Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUpActivity.this , "successfully account created" , Toast.LENGTH_SHORT).show();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user != null){
                                    if (!user.isEmailVerified()){
                                        user.sendEmailVerification();
                                        Toast.makeText(SignUpActivity.this ,"please verify your Email before log in." , Toast.LENGTH_SHORT).show();
                                    }
                                }
                                startActivity(new Intent(SignUpActivity.this , LogInActivity.class));
                            }
                        }
                    });
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this , LogInActivity.class));
            }
        });
    }

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
