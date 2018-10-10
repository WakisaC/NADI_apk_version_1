package com.example.wakisa.nadi_v1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteMain extends AppCompatActivity {

    private ListView mListViewNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mListViewNotes= (ListView)findViewById(R.id.main_listview_notes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_note:
                //start a new note
                Intent newNoteActivity = new Intent(this,NoteActivity.class);
                startActivity(newNoteActivity);
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewNotes.setAdapter(null);
        ArrayList<Note> notes = Utilities.getAllSavedNotes(this);
        if (notes == null || notes.size()==0){
            Toast.makeText(this, "Sorry no notes saved", Toast.LENGTH_LONG).show();
            return;
        }else {
            NoteAdapter na = new NoteAdapter(this,R.layout.item_note,notes);
            mListViewNotes.setAdapter(na);

            mListViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    String fileName =((Note) mListViewNotes.getItemAtPosition(position)).getmDateTime()+Utilities.FILE_EXTENSION;
                    Intent viewNoteIntent = new Intent(getApplicationContext(),NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);
                }
            });

        }
    }
}
