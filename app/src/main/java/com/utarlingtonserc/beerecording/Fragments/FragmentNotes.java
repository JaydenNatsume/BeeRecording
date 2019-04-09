package com.utarlingtonserc.beerecording.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.utarlingtonserc.beerecording.Adapters.NotesAdapter;
import com.utarlingtonserc.beerecording.Database.DatabaseSQL;
import com.utarlingtonserc.beerecording.Helper.NoteItem;
import com.utarlingtonserc.beerecording.MainActivity;
import com.utarlingtonserc.beerecording.NoteActivity;
import com.utarlingtonserc.beerecording.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotes extends Fragment {

    public ArrayList<String> enrolledCourses;
    private RecyclerView recyclerView;
    private NotesAdapter mAdapter;
    private DatabaseSQL myDB;
    private View view;
    private FloatingActionButton create_note;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notes, container, false);

        myDB = new DatabaseSQL(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        enrolledCourses = myDB.fetchNotesTitle();
        List<NoteItem> CoursesList = new ArrayList<NoteItem>();

        if(enrolledCourses.size() > 0){
            for(int i=0; i < enrolledCourses.size(); i++){
                CoursesList.add(new NoteItem(getContext().getResources().getIdentifier("icon_notes", "drawable", getContext().getPackageName()), enrolledCourses.get(i)));
            }
        }

        displayTutorials(CoursesList);

        create_note = view.findViewById(R.id.floating_button);
        create_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addItems();
                showCreateDialog();
            }
        });


        return view;
    }

    private void displayTutorials(List<NoteItem> tutorialsList){
        mAdapter = new NotesAdapter(view.getContext(), tutorialsList);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, NoteItem obj, int position) {
                Intent intent = new Intent(getActivity(), NoteActivity.class);
                intent.putExtra("title", obj.getName().trim());
                getActivity().startActivity(intent);
            }
        });
    }

    public void showCreateDialog(){

        final Dialog alertDialog = new Dialog(getActivity());
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setContentView(R.layout.dialog_create_list);
        alertDialog.setTitle("My Custom Dialog");

        final EditText Title = (EditText)alertDialog.findViewById(R.id.note_title);
        Button Cancel = (Button)alertDialog.findViewById(R.id.button_cancel);
        Button Yes = (Button)alertDialog.findViewById(R.id.button_create);

        Cancel.setEnabled(true);
        Yes.setEnabled(true);

        Cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.dismiss();
            }
        });

        Yes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Title.getText().toString().length() > 0){
                    Toast.makeText(getActivity(), "Note Created", Toast.LENGTH_LONG).show();
                    myDB.createNote(Title.getText().toString().trim(), "-");
                    alertDialog.dismiss();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                mAdapter.notifyDataSetChanged();
            }
        });

        alertDialog.show();
        alertDialog.getWindow().setLayout((6 * width)/7, RecyclerView.LayoutParams.WRAP_CONTENT);
    }
}
