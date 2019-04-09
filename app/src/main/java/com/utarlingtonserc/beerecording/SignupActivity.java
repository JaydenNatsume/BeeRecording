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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utarlingtonserc.beerecording.Helper.UserInformation;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutAddress;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutMobile;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirm;

    private EditText _nameText;
    private EditText _addressText;
    private EditText _emailText;
    private EditText _mobileText;
    private EditText _passwordText;
    private EditText _reEnterPasswordText;
    private Button _signupButton;
    private TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        _nameText = (EditText) findViewById(R.id.input_name);
        _addressText = (EditText) findViewById(R.id.input_address);
        _emailText = (EditText) findViewById(R.id.input_email);
        _mobileText = (EditText) findViewById(R.id.input_mobile);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _reEnterPasswordText = (EditText) findViewById(R.id.input_reEnterPassword);

        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutAddress = (TextInputLayout) findViewById(R.id.textInputLayoutAddress);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutMobile = (TextInputLayout) findViewById(R.id.textInputLayoutMobile);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirm = (TextInputLayout) findViewById(R.id.textInputLayoutConfirm);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegistration();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.relativeLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }

    public void userRegistration() {

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(!checkValidInput()){
            return;
        }

        // TODO: Implement your own signup logic here.


        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    // User is successfully registered and logged in.
                    // we will start the profile activity from here
                    // right now let's display a toast only
                    Toast.makeText(SignupActivity.this, "Successful! Please login", Toast.LENGTH_SHORT).show();
                    saveUserInformation();
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(SignupActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean checkValidInput(){

        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError("Required Field!");
            valid = false;
        }

        if(name.length() < 3){
            _nameText.setError("Must be at least 3 Characters Long");
            valid = false;
        }

        if (address.isEmpty()) {
            _addressText.setError("Required Field!");
            valid = false;
        }

        if (email.isEmpty()) {
            _emailText.setError("Required Field!");
            valid = false;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _emailText.setError("Invalid Email Address");
            valid = false;
        }

        if (mobile.isEmpty()) {
            _mobileText.setError("Required Field!");
            valid = false;
        }

        if(mobile.length()!= 10){
            _mobileText.setError("Invalid Mobile Number");
            valid = false;
        }

        if (password.isEmpty()) {
            _passwordText.setError("Required Field!");
            valid = false;
        }

        if(password.length() < 4 || password.length() > 10){
            _passwordText.setError("Must be Between 4 and 10 Alphanumeric Characters");
            valid = false;
        }

        if (reEnterPassword.isEmpty()) {
            _reEnterPasswordText.setError("Required Field!");
            valid = false;
        }

        if(!(reEnterPassword.equals(password))){
            _reEnterPasswordText.setError("Passwords do not Match");
            valid = false;
        }

        return valid;
    }


    public void saveUserInformation(){

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String email = _emailText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String password = _passwordText.getText().toString();
        String reEnterPassword = _reEnterPasswordText.getText().toString();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        UserInformation userInformation = new UserInformation(user.getUid(), name, address, email, mobile, password);

        databaseReference.child(user.getUid()).setValue(userInformation);

    }
}