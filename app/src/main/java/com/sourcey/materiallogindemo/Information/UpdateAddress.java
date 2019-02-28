package com.sourcey.materiallogindemo.Information;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sourcey.materiallogindemo.R;

public class UpdateAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_address);

        EditText address_edit = findViewById(R.id.information_address);
        address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save and update address to database.


                // After save the change of address, back to last Fragment.
                UpdateAddress.this.finish();
            }
        });
    }
}
