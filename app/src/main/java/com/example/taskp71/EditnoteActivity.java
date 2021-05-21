package com.example.taskp71;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taskp71.Note;
import com.example.taskp71.Database;

public class EditnoteActivity extends AppCompatActivity {

    EditText noteContentEditText;
    Button btn_update, btn_delete;
    Database db;
    String content;
    Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        noteContentEditText = findViewById(R.id.noteEditor);
        btn_update = findViewById(R.id.updateButton);
        btn_delete = findViewById(R.id.deleteButton);
        db = new Database(this);

        Intent getContentIntent = getIntent();

        String note_Content = getContentIntent.getStringExtra(unit.DESCRIPTION);

        Integer note_ID = getContentIntent.getIntExtra(unit.NOTE_ID,0);

        noteContentEditText.setText(note_Content);

        //create update button click event
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = noteContentEditText.getText().toString();
                id = note_ID;

                int updateIndex = db.editNote(new Note(id, content));
                if (updateIndex > 0) {
                    Toast.makeText(EditnoteActivity.this, "You update notes successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditnoteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EditnoteActivity.this,ListnoteActivity.class);
                startActivity(intent);
                finish();


            }
        });

        //create delete button click event
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content = noteContentEditText.getText().toString();
                id = note_ID;

                int deleteIndex = db.deleteNote(new Note(id, content));
                if (deleteIndex > 0) {
                    Toast.makeText(EditnoteActivity.this, "You delete notes successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditnoteActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(EditnoteActivity.this,ListnoteActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
}
