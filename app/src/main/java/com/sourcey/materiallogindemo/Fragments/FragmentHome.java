package com.sourcey.materiallogindemo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sourcey.materiallogindemo.Adapter.WatchlistAdapter;
import com.sourcey.materiallogindemo.ListView.WatchList;
import com.sourcey.materiallogindemo.R;
import com.sourcey.materiallogindemo.WatchListDetails;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private List<WatchList> watchLists = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initWatchList();

        final WatchlistAdapter adapter = new WatchlistAdapter(getActivity(), watchLists);
        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // 设置item点击监听事件，点击item跳转至商品详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListViewHome listViewHome = listViewHomes.get(position);  // listViewHome.getHomeTitle()获取点击item的标题
                Intent intent = new Intent(getActivity(), WatchListDetails.class);
                adapter.clear();
                startActivity(intent);
            }
        });

        
        return view;
    }

    private void initWatchList() {
        WatchList watchList1 = new WatchList("AAPL", "174.87");
        watchLists.add(watchList1);

        WatchList watchList2 = new WatchList("GE", "10.88");
        watchLists.add(watchList2);

        WatchList watchList3 = new WatchList("PYPL", "97.86");
        watchLists.add(watchList3);
    }

}
