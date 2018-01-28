package com.example.antonruban.notes.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.antonruban.notes.R;
import com.example.antonruban.notes.adapter.NotesAdapter;
import com.example.antonruban.notes.database.DataBaseHelper;
import com.example.antonruban.notes.detail.DetailActivity;
import com.example.antonruban.notes.model.Note;

import java.util.ArrayList;

public class MainActivity extends Activity implements NotesAdapter.ItemClickListener {

    ArrayList<Note> listNote;
    private NotesAdapter adapter;
    private RecyclerView recyclerView;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onResume() {
        super.onResume();
        RefreshNotes();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton fab = (ImageButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailActivity = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(detailActivity);
            }
        });
        dataBaseHelper = new DataBaseHelper(this);
        listNote = new ArrayList<Note>();
        recyclerView = (RecyclerView) findViewById(R.id.notesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void RefreshNotes() {
        listNote.clear();
        listNote =  dataBaseHelper.getAllNotes();
        adapter = new NotesAdapter(this, listNote);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("MyObj", listNote.get(position));
        startActivity(intent);
    }
}
