package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.details.NotesDetailsActivity;
import com.example.mynotes.ui.details.NotesDetailsFragment;
import com.example.mynotes.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.OnNotesClicked {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onNotesOnClicked(Notes notes) {

        if (getResources().getBoolean(R.bool.isLandscape)) {

        } else {
            Intent intent = new Intent(this, NotesDetailsActivity.class);
            intent.putExtra(NotesDetailsActivity.ARG_NOTE, notes);
            startActivity(intent);

        }

    }

}