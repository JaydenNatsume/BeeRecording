package com.sourcey.materiallogindemo.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sourcey.materiallogindemo.Information.UpdateUsername;
import com.sourcey.materiallogindemo.R;

public class FragmentInformation extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        EditText username_edit = view.findViewById(R.id.information_name);
        username_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent username_intent = new Intent(getActivity(), UpdateUsername.class);
                startActivity(username_intent);
            }
        });

        return view;
    }
}
