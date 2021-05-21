package com.example.taskp71;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

//import com.example.taskp71.Note;
//import com.example.taskp71.unit;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, unit.DATABASE_NAME, null, unit.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTE_TABLE = "CREATE TABLE " + unit.TABLE_NAME + "(" + unit.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + unit.DESCRIPTION + " TEXT)";

        db.execSQL(CREATE_NOTE_TABLE);

    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;

        db.execSQL("DROP TABLE IF EXISTS "+unit.TABLE_NAME);
        onCreate(db);
    }

    public long insertNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(unit.DESCRIPTION, note.getNote_desc());
        long newRowId = db.insert(unit.TABLE_NAME, null, contentValues);
        db.close();

        return newRowId;
    }



    public List<Note> fetchAllNotes() {
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + unit.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setNote_id(cursor.getInt(0));
                note.setNote_desc(cursor.getString(1));

                noteList.add(note);
            } while (cursor.moveToNext());
        }
        return noteList;
    }

    public int editNote(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(unit.NOTE_ID, note.getNote_id());
        contentValues.put(unit.DESCRIPTION, note.getNote_desc());

        return db.update(unit.TABLE_NAME, contentValues, unit.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }


    public int deleteNote(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(unit.TABLE_NAME, unit.NOTE_ID + "=?", new String[]{String.valueOf(note.getNote_id())});
    }




}
