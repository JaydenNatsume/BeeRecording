package com.sourcey.materiallogindemo.Information;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sourcey.materiallogindemo.R;

public class UpdateUsername extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_username);

        EditText username_edit = findViewById(R.id.information_name);
        username_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save and update username to database.


                // After save the change of username, back to last Fragment.
                UpdateUsername.this.finish();
            }
        });
    }
}
