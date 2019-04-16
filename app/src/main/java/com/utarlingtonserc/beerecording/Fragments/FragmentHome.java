package com.utarlingtonserc.beerecording.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utarlingtonserc.beerecording.Adapters.WatchlistAdapter;
import com.utarlingtonserc.beerecording.Helper.WatchList;
import com.utarlingtonserc.beerecording.R;
import com.utarlingtonserc.beerecording.WatchListDetailsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.utarlingtonserc.beerecording.MainActivity;

public class FragmentHome extends Fragment implements WatchlistAdapter.ItemClickListener{


    private WatchlistAdapter watchlistAdapter;
    private List<WatchList> watchLists = new ArrayList<>();
    private List<WatchList> homeList1 = new ArrayList<>();
    private List<WatchList> homeList;
    public RecyclerView watchlist_recyclerview;
    private String symbol_quote;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        watchlist_recyclerview = view.findViewById(R.id.recyclerView);
        watchlist_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        homeList = MainActivity.get_list();

        if (homeList.isEmpty()){
            // 从API获取json数据
            sendRequestWithHttpURLConnection();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.d("FragmentHome", "sleep failed");
            }
        }

        // 搜索
        SearchView searchView = view.findViewById(R.id.search_view);

        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getActivity(),"asidfjasd.", Toast.LENGTH_SHORT).show();
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty()){
                    watchLists = homeList;
                    Log.d("FragmentHome", watchLists.toString());
                    watchlistAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),"Blank input.", Toast.LENGTH_SHORT).show();
                }else if (s.equals("a")){
                    watchLists = homeList1;
                    Log.d("FragmentHome", watchLists.toString());
                    watchlistAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),"No match.", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        homeList1.clear();
        Log.d("FragmentHome", symbol_quote);
        homeList1.add(new WatchList("AAPL", symbol_quote));

        watchLists = homeList;

        watchlistAdapter = new WatchlistAdapter(getContext(), watchLists);

        watchlist_recyclerview.setAdapter(watchlistAdapter);

        watchlistAdapter.setClickListener(this);

        return view;
    }

    private void sendRequestWithHttpURLConnection(){
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                String urlHead = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=";
                String urlEnd = "&interval=1min&apikey=3WNQE8NDKHWW1NFJ";
                String[] symbol_list = {"MSFT", "AAPL", "PYPL", "GOOG"};
                for (String symbol : symbol_list) {
                    try {
                        URL url = new URL(urlHead + symbol + urlEnd);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(5000);
                        connection.setReadTimeout(5000);
                        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                        byte[] data = new byte[1024];
                        InputStream in = connection.getInputStream();
                        // 下面对获取到的输入流进行读取，转化为json对象
                        int len;
                        while ((len = in.read(data)) != -1) {
                            outStream.write(data, 0, len);
                        }

                        in.close();
                        outStream.close();
                        String response = new String(outStream.toByteArray());
                        showResponse(response);
                    } catch (Exception e) {
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
            }
        }).start();
    }

    private void showResponse(final String response) {
        JSONObject object = null;
        try {
            object = new JSONObject(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(object != null) {
            JSONObject ObjectInfo = object.optJSONObject("Meta Data");
            JSONObject ObjectInfo_times = object.optJSONObject("Time Series (1min)");
            if(ObjectInfo == null){
                Toast.makeText(getActivity(),"API only 5 t/m.", Toast.LENGTH_SHORT).show();
            }else {
                // 获取symbol
                String symbol = ObjectInfo.optString("2. Symbol");
                // 获取Time Series (1min)的第一层对象
                Iterator<String> iter = ObjectInfo_times.keys();
                String key = iter.next();
                JSONObject ObjectInfo_quote = ObjectInfo_times.optJSONObject(key);
                String quote = ObjectInfo_quote.optString("1. open");
                if (symbol.equals("AAPL")){
                    symbol_quote = quote;
                }
                // 初始化watchlist中list的内容
                initWatchList(symbol, quote);
            }
        }


    }

    private void initWatchList(String symbol, String quote) {
        homeList.add(new WatchList(symbol, quote));
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
