package com.example.taskp71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    Button New, List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        New = findViewById(R.id.buttonNew);
        List = findViewById(R.id.buttonList);
    }

    public void Newnote(View view){
        Intent intent = new Intent(this,NewnoteActivity.class);
        startActivity(intent);
    }

    public void Listnote(View view){
        Intent intent = new Intent(this,ListnoteActivity.class);
        startActivity(intent);
    }
}
