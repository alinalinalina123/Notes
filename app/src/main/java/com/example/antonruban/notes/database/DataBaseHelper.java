package com.example.antonruban.notes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.antonruban.notes.model.Note;

import java.util.ArrayList;
import java.util.List;

/**
  @author antonruban on 27.01.2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DataValues.DATABASE_NAME, null, DataValues.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataValues.TABLE_NOTES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DataValues.TABLE_NOTES_DROP);
        onCreate(db);
    }

    public void addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues noteValues = new ContentValues();
        noteValues.put(DataValues.NOTES_TITLE, note.getTitle());
        noteValues.put(DataValues.NOTES_DESCRIPTION, note.getDescription());
        db.insert(DataValues.TABLE_NOTES, null, noteValues);
        db.close();

    }

    public void updateNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues noteValues = new ContentValues();
        noteValues.put(DataValues.NOTES_ID,note.getId());
        noteValues.put(DataValues.NOTES_TITLE, note.getTitle());
        noteValues.put(DataValues.NOTES_DESCRIPTION, note.getDescription());
        db.update(DataValues.TABLE_NOTES, noteValues, DataValues.NOTES_ID + " = ?", new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public void deleteNote(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + DataValues.TABLE_NOTES + " WHERE " + DataValues.NOTES_ID + " = '" + id + "'";
        db.execSQL(deleteQuery);
        db.close();
    }

    public ArrayList<Note> getAllNotes() {

        ArrayList<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + DataValues.TABLE_NOTES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
                note.setDescription(cursor.getString(2));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return notes;
    }
}
