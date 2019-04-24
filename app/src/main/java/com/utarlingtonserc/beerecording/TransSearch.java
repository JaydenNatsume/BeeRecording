package com.utarlingtonserc.beerecording;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utarlingtonserc.beerecording.Adapters.TransPerformanceAdapter;
import com.utarlingtonserc.beerecording.Helper.PerformanceList;

import java.util.ArrayList;
import java.util.List;

public class TransSearch extends AppCompatActivity {

    private List<PerformanceList> searchLists = new ArrayList<>();
    public RecyclerView search_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_search);
        search_list_recyclerview = findViewById(R.id.trans_search_recycler_view);
        search_list_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        // 增加默认分割线
        search_list_recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        initBuyList();
        TransPerformanceAdapter transPerformanceAdapter = new TransPerformanceAdapter(this, searchLists);
        search_list_recyclerview.setAdapter(transPerformanceAdapter);
    }

    private void initBuyList() {
        searchLists.add(new PerformanceList("MSFT", "121.0500", "4/10/2019"));
        searchLists.add(new PerformanceList("AAPL", "199.0000", "4/10/2019"));
        searchLists.add(new PerformanceList("GOOG", "132.9000", "4/10/2019"));
        searchLists.add(new PerformanceList("PYPL", "108.1000", "4/12/2019"));
    }
}
