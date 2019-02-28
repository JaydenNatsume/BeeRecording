package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WatchListDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list_details);

        TextView tv_news = findViewById(R.id.tv_news);
        tv_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WatchListDetails.this, WatchListDetails.class);
                startActivity(intent);
            }
        });
    }
}
