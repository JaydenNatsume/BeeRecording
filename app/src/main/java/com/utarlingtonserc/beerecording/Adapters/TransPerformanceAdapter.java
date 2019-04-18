package com.utarlingtonserc.beerecording.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.utarlingtonserc.beerecording.Helper.PerformanceList;
import com.utarlingtonserc.beerecording.R;

import java.util.List;

public class TransPerformanceAdapter extends RecyclerView.Adapter<TransPerformanceAdapter.ViewHolder> {

    private List<PerformanceList> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private AdapterView.OnItemLongClickListener mLongClickListener;
    private AdapterView adapterView;


    // data is passed into the constructor
    public TransPerformanceAdapter(Context context, List<PerformanceList> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_trans_performance_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String tickerSymbol = mData.get(position).getTickerSymbol();
        String transPerformance = mData.get(position).getTransPerformance();
        String transDate = mData.get(position).getTransDate();
        holder.symbol.setText(tickerSymbol);
        holder.performance.setText(transPerformance);
        holder.date.setText(transDate);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView symbol;
        TextView performance;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView);
            symbol = (TextView) itemView.findViewById(R.id.trans_performance_symbol);
            performance = (TextView) itemView.findViewById(R.id.trans_performance);
            date = (TextView) itemView.findViewById(R.id.trans_performance_date);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View view){
            if(mLongClickListener != null){
                mLongClickListener.onItemLongClick(adapterView, view, getAdapterPosition(), getItemId());
            }
            return true;
        }

    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id).getTickerSymbol();
    }

    public void setLongClickListener(AdapterView.OnItemLongClickListener itemLongClickListener){
        this.mLongClickListener = itemLongClickListener;
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
