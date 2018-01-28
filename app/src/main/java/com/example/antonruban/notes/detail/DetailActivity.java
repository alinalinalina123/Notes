package com.example.antonruban.notes.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.antonruban.notes.R;
import com.example.antonruban.notes.database.DataBaseHelper;
import com.example.antonruban.notes.model.Note;

public class DetailActivity extends Activity {

    EditText edtTitle, edtDescription;
    Button deleteButton;
    String title, description;
    int noteId;
    Boolean isUpdateMode;
    private Note noteUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        deleteButton = (Button) findViewById(R.id.deleteButton);
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        isUpdateMode = false;
        noteUpdate = (Note)getIntent().getSerializableExtra("MyObj");
        if(noteUpdate != null) {
            edtTitle.setText(noteUpdate.getTitle());
            edtDescription.setText(noteUpdate.getDescription());
            isUpdateMode = true;
            deleteButton.setVisibility(View.VISIBLE);
        }


    }

    public void saveNotes(View view) {

        title = edtTitle.getText().toString();
        description = edtDescription.getText().toString();

        if (!isValidNote()) {
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setDescription(description);

        DataBaseHelper databaseHandler = new DataBaseHelper(this);
        if (!isUpdateMode) {
            databaseHandler.addNote(note);
            Toast.makeText(this, "Note Added Successfully", Toast.LENGTH_LONG).show();
        } else {
            note.setId(noteUpdate.getId());
            databaseHandler.updateNote(note);
            Toast.makeText(this, "Note Updated Successfully", Toast.LENGTH_LONG).show();
        }

        super.onBackPressed();
    }

    private Boolean isValidNote() {
        if (title.isEmpty() && description.isEmpty()) {
            Toast.makeText(this, "Please Enter Title and Description", Toast.LENGTH_SHORT).show();
            return false;
        } else if (title.isEmpty()) {
            Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show();
            return false;
        } else if (description.isEmpty()) {
            Toast.makeText(this, "Please Enter Description", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void deleteNotes(View view) {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Delete Note").setMessage("Are you sure you want to delete this note?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBaseHelper databaseHandler = new DataBaseHelper(DetailActivity.this);
                        databaseHandler.deleteNote(String.valueOf(noteUpdate.getId()));
                        DetailActivity.this.finish();
                    }

                }).setNegativeButton("No", null).show();
    }
}
