package com.example.taskp71;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskp71.Note;
import com.example.taskp71.Database;



public class NewnoteActivity extends AppCompatActivity {

    EditText noteInput;
    Button saveButton;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        noteInput = findViewById(R.id.note_content);
        saveButton = findViewById(R.id.saveButton);
        db = new Database(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = noteInput.getText().toString();

                if (content.length() != 0){
                    long result = db.insertNote(new Note(content));

                    if (result > 0){
                        Toast.makeText(NewnoteActivity.this, "You create note successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(NewnoteActivity.this, "Create note unsuccessfully!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(NewnoteActivity.this, "The content of note can not be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
