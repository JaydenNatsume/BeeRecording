package com.utarlingtonserc.beerecording;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.utarlingtonserc.beerecording.Adapters.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class WatchListDetailsActivity extends AppCompatActivity {

    private TextView stock_symbol;
    private TextView stock_price;
    private RecyclerView recyclerView;

    private ImageButton stockPrice;
    private ImageButton newsfeed;
    private ImageButton removeButton;

    private HistoryAdapter historyAdapter;
    private List<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist_details);

        stock_symbol = (TextView) findViewById(R.id.ticker_symbol);
        stock_price = (TextView) findViewById(R.id.price);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        stockPrice = (ImageButton) findViewById(R.id.stock_price);
        newsfeed = (ImageButton) findViewById(R.id.button_news);
        removeButton = (ImageButton) findViewById(R.id.button_remove);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initHistoryList();

        newsfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WatchListDetailsActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        historyAdapter = new HistoryAdapter(this, historyList);

        recyclerView.setAdapter(historyAdapter);

        stock_symbol.setText(getStockSymbol());
        stock_price.setText("174.87");

    }

    public void initHistoryList(){
        historyList.add("123.22");
        historyList.add("89.75");
        historyList.add("145.8");
        historyList.add("195.21");
        historyList.add("201.67");
        historyList.add("157.11");
    }

    public String getStockSymbol(){
        Intent intent = getIntent();
        String symbol = intent.getStringExtra("symbol");
        return symbol;
    }

}
