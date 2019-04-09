package com.utarlingtonserc.beerecording.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.utarlingtonserc.beerecording.Database.DatabaseSQL;
import com.utarlingtonserc.beerecording.EditInfoActivity;
import com.utarlingtonserc.beerecording.Helper.UserInformation;
import com.utarlingtonserc.beerecording.NoteActivity;
import com.utarlingtonserc.beerecording.R;

public class FragmentInformation extends Fragment {

    private TextView userName;
    private TextView userAddress;
    private TextView userEmail;
    private TextView userMobile;

    private DatabaseSQL myDB;

    DatabaseReference databaseReference;
    UserInformation userInformation;
    FirebaseUser user;
    String uid;

    private FloatingActionButton edit_info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        userName = (TextView) view.findViewById(R.id.user_name);
        userAddress = (TextView) view.findViewById(R.id.user_address);
        userEmail = (TextView) view.findViewById(R.id.user_email);
        userMobile = (TextView) view.findViewById(R.id.user_phone);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        myDB = new DatabaseSQL(getActivity());

        edit_info = view.findViewById(R.id.floating_button);
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addItems();
                Intent intent = new Intent(getActivity(), EditInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String userNameString = dataSnapshot.child(uid).child("name").getValue(String.class);
                String userAddressString = dataSnapshot.child(uid).child("address").getValue(String.class);
                String userEmailString = dataSnapshot.child(uid).child("email").getValue(String.class);
                String userMobileString = dataSnapshot.child(uid).child("mobile").getValue(String.class);

                userName.setText(userNameString);
                userAddress.setText(userAddressString);
                userEmail.setText(userEmailString);
                userMobile.setText(userMobileString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Network ERROR. Please check your connection", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }
}
