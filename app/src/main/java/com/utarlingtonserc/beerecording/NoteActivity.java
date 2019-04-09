package com.utarlingtonserc.beerecording;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.utarlingtonserc.beerecording.Database.DatabaseSQL;
import com.utarlingtonserc.beerecording.Fragments.FragmentHome;

public class NoteActivity extends AppCompatActivity {

    private EditText noteContent;
    private TextView noteTitle;
    private DatabaseSQL myDB;
    private FloatingActionButton create_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        myDB = new DatabaseSQL(NoteActivity.this);
        noteTitle = (TextView) findViewById(R.id.note_title);
        noteContent = (EditText) findViewById(R.id.note_content);

        noteTitle.setText(getNoteName());
        noteContent.setText(myDB.getNote(getNoteName()));

        create_note = (FloatingActionButton) findViewById(R.id.floating_button);

        create_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.createNote(noteTitle.getText().toString(), noteContent.getText().toString());
                Toast.makeText(getApplicationContext(), "Note Saved..", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public String getNoteName(){
        Intent intent = getIntent();
        String noteName = intent.getStringExtra("title");
        return noteName;
    }
}
