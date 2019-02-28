package com.sourcey.materiallogindemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sourcey.materiallogindemo.ListView.WatchList;
import com.sourcey.materiallogindemo.R;

import java.util.List;

public class WatchlistAdapter extends ArrayAdapter<WatchList> {

    public WatchlistAdapter(Context context, List<WatchList> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WatchList watchList = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview_watchlist, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tickerSymbol = view.findViewById(R.id.ticker_symbol);
            viewHolder.liveQuote = view.findViewById(R.id.live_quote);
            view.setTag(viewHolder);    // 将ViewHolder存在View中
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();    // 重新获取ViewHolder
        }


        viewHolder.tickerSymbol.setText(watchList.getTickerSymbol());
        viewHolder.liveQuote.setText(watchList.getLiveQuote());

        return view;
    }

    class ViewHolder {
        TextView tickerSymbol;
        TextView liveQuote;
    }
}