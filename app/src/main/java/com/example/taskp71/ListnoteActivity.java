package com.example.taskp71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


//import com.example.taskp71.Note;
//import com.example.taskp71.Database;
//import com.example.taskp71.unit;

import java.util.ArrayList;
import java.util.List;

public class ListnoteActivity extends AppCompatActivity {

    ListView noteListView;
    ArrayList<String> noteArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        noteListView = findViewById(R.id.noteList);
        noteArrayList = new ArrayList<>();

        Database db = new Database(ListnoteActivity.this);

        List<Note> noteList = db.fetchAllNotes();

        for (Note note : noteList) {
            noteArrayList.add(note.getNote_desc());
        }
        //set adapter of list

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,noteArrayList);

        noteListView.setAdapter(adapter);

        //use setOnItemClickListener

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent EditorIntent = new Intent(ListnoteActivity.this, EditnoteActivity.class);

                EditorIntent.putExtra(unit.NOTE_ID, noteList.get(position).getNote_id());
                EditorIntent.putExtra(unit.DESCRIPTION, noteList.get(position).getNote_desc());

                startActivityForResult(EditorIntent, 1);

                finish();
            }
        });


    }






}