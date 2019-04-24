package com.utarlingtonserc.beerecording.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.utarlingtonserc.beerecording.R;
import com.utarlingtonserc.beerecording.TransBuy;
import com.utarlingtonserc.beerecording.TransPerformance;
import com.utarlingtonserc.beerecording.TransSearch;
import com.utarlingtonserc.beerecording.TransSell;

import com.utarlingtonserc.beerecording.DateUtils;
import java.util.Calendar;


public class FragmentTransaction extends Fragment {

    private TextView recordDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);

        recordDate = view.findViewById(R.id.record_date);

        Button buyBtn = view.findViewById(R.id.buy_btn);
        Button sellBtn = view.findViewById(R.id.sell_btn);
        Button performanceBtn = view.findViewById(R.id.performance_btn);
        Button recordSearchBtn = view.findViewById(R.id.record_search_btn);

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
                Intent performance_intent = new Intent(getActivity(), TransPerformance.class);
                startActivity(performance_intent);
            }
        });

        recordDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                recordDate.setText(DateUtils.date2String(calendar.getTime(), DateUtils.YMD_FORMAT));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        recordSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent performance_intent = new Intent(getActivity(), TransSearch.class);
                startActivity(performance_intent);
            }
        });

        return view;
    }
}