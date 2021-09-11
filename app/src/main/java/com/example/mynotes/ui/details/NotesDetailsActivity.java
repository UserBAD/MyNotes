package com.example.mynotes.ui.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mynotes.R;
import com.example.mynotes.domain.Notes;

import android.os.Bundle;

public class NotesDetailsActivity extends AppCompatActivity {

    public static final String ARG_NOTE = "ARG_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_details);

        if (getResources().getBoolean(R.bool.isLandscape)) {
            finish();
        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();

            Notes notes = getIntent().getParcelableExtra(ARG_NOTE);

            fragmentManager.beginTransaction()
                    .replace(R.id.container, NotesDetailsFragment.newInstance(notes), null)
                    .commit();
        }
    }

}