package com.utarlingtonserc.beerecording.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utarlingtonserc.beerecording.Adapters.WatchlistAdapter;
import com.utarlingtonserc.beerecording.Helper.WatchList;
import com.utarlingtonserc.beerecording.R;
import com.utarlingtonserc.beerecording.WatchListDetailsActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements WatchlistAdapter.ItemClickListener{


    private WatchlistAdapter watchlistAdapter;
    private List<WatchList> watchLists = new ArrayList<>();
    public RecyclerView watchlist_recyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        watchlist_recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        watchlist_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        initWatchList();

        watchlistAdapter = new WatchlistAdapter(getContext(), watchLists);

        watchlist_recyclerview.setAdapter(watchlistAdapter);

        watchlistAdapter.setClickListener(this);

        // 从API获取json数据
        sendRequestWithHttpURLConnection();

        // 搜索
        SearchView searchView = view.findViewById(R.id.search_view);

        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity(),"Network ERROR. Please check your connection", Toast.LENGTH_SHORT).show();
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(getActivity(),"Please check your connection", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }

    private void sendRequestWithHttpURLConnection(){
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=1min&apikey=demo");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();

                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        Log.d("FragmentHome", response);

//        尝试解析json数据，结果：失败！
//        JsonReader reader = new JsonReader(new Strin gReader(response));
//        reader.setLenient(true);
//        Log.d("FragmentHome", "exe?");
//        try {
//            reader.beginArray();
//            while (reader.hasNext()) {
//                reader.beginObject();
//                while (reader.hasNext()) {
//                    String tagName = reader.nextName();
//                    Log.d("FragmentHome", tagName);
//                    Log.d("FragmentHome", reader.nextString());
//                }
//                reader.endObject();
//            }
//            reader.endArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void initWatchList() {
        watchLists.add(new WatchList("AAPL", "174.87"));
        watchLists.add(new WatchList("GE  ", "  10.88"));
        watchLists.add(new WatchList("PYPL", "97.86"));
    }

    @Override
    public void onItemClick(View view, int position){
        Intent intent = new Intent(getActivity().getBaseContext(), WatchListDetailsActivity.class);
        intent.putExtra("symbol", watchlistAdapter.getItem(position).trim());
        getActivity().startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position){
        //Toast.makeText(getActivity(), "Long Click", Toast.LENGTH_LONG).show();
    }
}
