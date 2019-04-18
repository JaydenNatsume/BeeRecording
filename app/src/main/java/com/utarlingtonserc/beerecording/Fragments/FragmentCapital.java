package com.utarlingtonserc.beerecording.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.utarlingtonserc.beerecording.R;

public class FragmentCapital extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_capital, container, false);

        textView = view.findViewById(R.id.capital_text_view);

        Button threeDaysBtn = view.findViewById(R.id.three_days_btn);
        Button pastWeekBtn = view.findViewById(R.id.past_week_btn);
        Button oneMonthBtn = view.findViewById(R.id.one_month_btn);
        Button threeMonthBtn = view.findViewById(R.id.three_month_btn);

        threeDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpannableString spannableStringThreeDays = new SpannableString("+612.36");
                spannableStringThreeDays.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),
                        0, spannableStringThreeDays.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableStringThreeDays);
            }
        });

        pastWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpannableString spannableString = new SpannableString("+187.52");
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")),
                        0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableString);
            }
        });

        oneMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpannableString spannableString = new SpannableString("-394.06");
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#84bd00")),
                        0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableString);
            }
        });

        threeMonthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpannableString spannableString = new SpannableString("-1059.40");
                spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#84bd00")),
                        0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableString);
            }
        });




        return view;
    }
}