package com.utarlingtonserc.beerecording;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utarlingtonserc.beerecording.Fragments.FragmentHome;
import com.utarlingtonserc.beerecording.Helper.UserInformation;

public class EditInfoActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userAddress;
    private TextView userEmail;
    private EditText userMobile;

    private Button submitButton;
    private String password;

    DatabaseReference databaseReference;
    UserInformation userInformation;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        userName = (EditText) findViewById(R.id.input_name);
        userAddress = (EditText) findViewById(R.id.input_address);
        userEmail = (TextView) findViewById(R.id.input_email);
        userMobile = (EditText) findViewById(R.id.input_mobile);
        submitButton = (Button) findViewById(R.id.btn_signup);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String userNameString = dataSnapshot.child(uid).child("name").getValue(String.class);
                String userAddressString = dataSnapshot.child(uid).child("address").getValue(String.class);
                String userEmailString = dataSnapshot.child(uid).child("email").getValue(String.class);
                String userMobileString = dataSnapshot.child(uid).child("mobile").getValue(String.class);

                password = dataSnapshot.child(uid).child("password").getValue(String.class);

                userName.setText(userNameString);
                userAddress.setText(userAddressString);
                userEmail.setText(userEmailString);
                userMobile.setText(userMobileString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditInfoActivity.this,"Network ERROR. Please check your connection", Toast.LENGTH_LONG).show();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validationCheck()){
                    UserInformation userInformation = new UserInformation(user.getUid(), userName.getText().toString(), userAddress.getText().toString(), userEmail.getText().toString(), userMobile.getText().toString(), password);
                    databaseReference.child(user.getUid()).setValue(userInformation);
                    Toast.makeText(EditInfoActivity.this, "Success..", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(EditInfoActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(EditInfoActivity.this, "Data Input Error..", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean validationCheck(){

        if(userName.getText().toString().isEmpty()){
            return false;
        }
        if(userAddress.getText().toString().isEmpty()){
            return false;
        }
        if(userEmail.getText().toString().isEmpty()){
            return false;
        }
        if(userMobile.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }
}
