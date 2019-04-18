package com.utarlingtonserc.beerecording;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utarlingtonserc.beerecording.Adapters.TransBuyAdapter;
import com.utarlingtonserc.beerecording.Adapters.TransPerformanceAdapter;
import com.utarlingtonserc.beerecording.Helper.BuyList;
import com.utarlingtonserc.beerecording.Helper.PerformanceList;

import java.util.ArrayList;
import java.util.List;

public class TransPerformance extends AppCompatActivity {
    private TransPerformanceAdapter transPerformanceAdapter;
    private List<PerformanceList> performanceLists = new ArrayList<>();
    public RecyclerView performance_list_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_performance);

        performance_list_recyclerview = findViewById(R.id.trans_performance);
        performance_list_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        initBuyList();
        transPerformanceAdapter = new TransPerformanceAdapter(this, performanceLists);
        performance_list_recyclerview.setAdapter(transPerformanceAdapter);
    }

    private void initBuyList() {
        performanceLists.add(new PerformanceList("MSFT", "121.0500", "4/09/2019"));
        performanceLists.add(new PerformanceList("AAPL", "199.0000", "4/10/2019"));
        performanceLists.add(new PerformanceList("GOOG", "1291.9000", "4/11/2019"));
        performanceLists.add(new PerformanceList("PYPL", "108.1000", "4/12/2019"));
        performanceLists.add(new PerformanceList("MSFT", "121.0500", "4/12/2019"));
        performanceLists.add(new PerformanceList("AAPL", "199.0000", "4/13/2019"));
        performanceLists.add(new PerformanceList("GOOG", "1291.9000", "4/14/2019"));
        performanceLists.add(new PerformanceList("PYPL", "108.1000", "4/15/2019"));
        performanceLists.add(new PerformanceList("MSFT", "121.0500", "4/09/2019"));
        performanceLists.add(new PerformanceList("AAPL", "199.0000", "4/10/2019"));
        performanceLists.add(new PerformanceList("GOOG", "1291.9000", "4/11/2019"));
        performanceLists.add(new PerformanceList("PYPL", "108.1000", "4/12/2019"));
        performanceLists.add(new PerformanceList("MSFT", "121.0500", "4/12/2019"));
        performanceLists.add(new PerformanceList("AAPL", "199.0000", "4/13/2019"));
        performanceLists.add(new PerformanceList("GOOG", "1291.9000", "4/14/2019"));
        performanceLists.add(new PerformanceList("PYPL", "108.1000", "4/15/2019"));
    }

}
