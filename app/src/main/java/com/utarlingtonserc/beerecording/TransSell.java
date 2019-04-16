package com.utarlingtonserc.beerecording;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utarlingtonserc.beerecording.Adapters.TransSellAdapter;
import com.utarlingtonserc.beerecording.Helper.BuyList;

import java.util.ArrayList;
import java.util.List;

public class TransSell extends AppCompatActivity {

    private TransSellAdapter transSellAdapter;
    private List<BuyList> sellLists = new ArrayList<>();
    public RecyclerView sell_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_sell);

        sell_list_recyclerview = findViewById(R.id.trans_sell);
        sell_list_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        initBuyList();
        transSellAdapter = new TransSellAdapter(this, sellLists);
        sell_list_recyclerview.setAdapter(transSellAdapter);


    }

    private void initBuyList() {
        sellLists.add(new BuyList("MSFT", "121.0500", "4/09/2019"));
        sellLists.add(new BuyList("AAPL", "199.0000", "4/10/2019"));
        sellLists.add(new BuyList("GOOG", "1291.9000", "4/11/2019"));
        sellLists.add(new BuyList("PYPL", "108.1000", "4/12/2019"));
        sellLists.add(new BuyList("MSFT", "121.0500", "4/12/2019"));
        sellLists.add(new BuyList("AAPL", "199.0000", "4/13/2019"));
        sellLists.add(new BuyList("GOOG", "1291.9000", "4/14/2019"));
        sellLists.add(new BuyList("PYPL", "108.1000", "4/15/2019"));
        sellLists.add(new BuyList("MSFT", "121.0500", "4/09/2019"));
        sellLists.add(new BuyList("AAPL", "199.0000", "4/10/2019"));
        sellLists.add(new BuyList("GOOG", "1291.9000", "4/11/2019"));
        sellLists.add(new BuyList("PYPL", "108.1000", "4/12/2019"));
        sellLists.add(new BuyList("MSFT", "121.0500", "4/12/2019"));
        sellLists.add(new BuyList("AAPL", "199.0000", "4/13/2019"));
        sellLists.add(new BuyList("GOOG", "1291.9000", "4/14/2019"));
        sellLists.add(new BuyList("PYPL", "108.1000", "4/15/2019"));
    }

}
