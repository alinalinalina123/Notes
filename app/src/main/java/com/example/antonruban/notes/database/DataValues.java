package com.example.antonruban.notes.database;

/**
 @author antonruban on 27.01.2018.
 */

public class DataValues {

    public static final int DATABASE_VERSION = 5;

    public static final String DATABASE_NAME = "notesDB";

    public static final String TABLE_NOTES = "notes";

    public static final String NOTES_ID = "id";
    public static final String NOTES_TITLE = "title";
    public static final String NOTES_DESCRIPTION = "description";

    public static final String TABLE_NOTES_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NOTES + "( " + NOTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTES_TITLE + " TEXT, " + NOTES_DESCRIPTION + " TEXT)";

    public static final String TABLE_NOTES_DROP = "DROP TABLE IF EXISTS " + TABLE_NOTES;
}
