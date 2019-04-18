package com.utarlingtonserc.beerecording.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.utarlingtonserc.beerecording.R;
import com.utarlingtonserc.beerecording.TransBuy;
import com.utarlingtonserc.beerecording.TransSell;

public class FragmentTransaction extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        Button buyBtn = view.findViewById(R.id.buy_btn);
        Button sellBtn = view.findViewById(R.id.sell_btn);
        Button performanceBtn = view.findViewById(R.id.performance_btn);

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buy_intent = new Intent(getActivity(), TransBuy.class);
                startActivity(buy_intent);
            }
        });

        sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sell_intent = new Intent(getActivity(), TransSell.class);
                startActivity(sell_intent);
            }
        });

        performanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performance_intent = new Intent(getActivity(), TransSell.class);
                startActivity(performance_intent);
            }
        });

        return view;
    }
}