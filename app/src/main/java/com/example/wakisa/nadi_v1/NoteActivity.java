package com.example.wakisa.nadi_v1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private EditText mEtTitle,mEtContent;
    private String mNoteFileName;
    private Note mLoadedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEtTitle=(EditText)findViewById(R.id.note_et_title);
        mEtContent=(EditText)findViewById(R.id.note_et_content);
        mNoteFileName = getIntent().getStringExtra("NOTE_FILE");

        if (mNoteFileName !=null && !mNoteFileName.isEmpty()){
            mLoadedNote = Utilities.getNoteByName(this,mNoteFileName);
            if (mLoadedNote !=null){
                mEtTitle.setText(mLoadedNote.getmTitle());
                mEtContent.setText(mLoadedNote.getmContent());
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_new,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_note_save:
                saveNote();
            break;
            case R.id.action_note_delete:
               deleteNote();
           break;
        }
        return  true;
    }
    private void saveNote(){
        Note note;

        if (mEtTitle.getText().toString().trim().isEmpty()|| mEtContent.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter a title and Content !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mLoadedNote ==null) {

             note = new Note(System.currentTimeMillis(), mEtTitle.getText().toString(), mEtContent.getText().toString());
        }else {
             note = new Note(mLoadedNote.getmDateTime(), mEtTitle.getText().toString(), mEtContent.getText().toString());
        }

            if (Utilities.saveNote(this, note)) {
                Toast.makeText(this, "Note saved successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error in saving Note, please make sure you have enough space on your device", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    private void deleteNote(){
        if (mLoadedNote == null){
            finish();
        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Are you sure?")
                    .setMessage("Delete "+mEtTitle.getText().toString() + "??")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utilities.deleteNote(getApplicationContext(),mLoadedNote.getmDateTime()+Utilities.FILE_EXTENSION);
                                Toast.makeText(NoteActivity.this, mEtTitle.getText().toString()+" is deleted !", Toast.LENGTH_LONG).show();
                                finish();
                            } })
                   .setNegativeButton("No",null)
                   .setCancelable(false);
            dialog.show();
           
        }
        }

}
