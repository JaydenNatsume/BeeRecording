package com.utarlingtonserc.beerecording;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.utarlingtonserc.beerecording.Helper.InputValidation;
import com.utarlingtonserc.beerecording.Helper.PreferenceUtils;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private InputValidation inputValidation;
    FirebaseAuth mAuth;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private EditText _emailText;
    private EditText _passwordText;

    private Button _loginButton;
    private TextView _signupLink;
    private TextView _resetPasswordLink;

    private ProgressBar loginProgressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        _resetPasswordLink = (TextView) findViewById(R.id.link_forgot_password);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInput_email);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInput_password);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgressBar.setVisibility(View.VISIBLE);
                userLogin();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                finish();
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });

        /*_resetPasswordLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                finish();
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });*/

        if (PreferenceUtils.getEmail(this) != null ){
            _emailText.setText(PreferenceUtils.getEmail(this));
            _passwordText.setText(PreferenceUtils.getPassword(this));
        }
        else{
        }

        findViewById(R.id.relativeLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });

        loginProgressBar = findViewById(R.id.login_progress_bar);
        loginProgressBar.setVisibility(View.GONE);
    }

    private void userLogin(){

        inputValidation = new InputValidation(LoginActivity.this);

        if(!inputValidation.isInputEditTextFilled(_emailText, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(_passwordText, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(_emailText, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }


        mAuth.signInWithEmailAndPassword(_emailText.getText().toString().trim(), _passwordText.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    // add flags to clear all the opened activities
                    // otherwise the app will come back to login activity if the user presses "down"
                    PreferenceUtils.saveEmail(_emailText.getText().toString().trim(), getApplicationContext());
                    PreferenceUtils.savePassword(_passwordText.getText().toString().trim(), getApplicationContext());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("email", _emailText.getText().toString().trim());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    loginProgressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
