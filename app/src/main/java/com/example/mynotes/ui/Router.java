package com.example.mynotes.ui;

import androidx.fragment.app.FragmentManager;

import com.example.mynotes.R;
import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.edit.EditNoteFragment;

public class Router {

    private final FragmentManager fragmentManager;

    public Router(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void showEditNote(Notes notes) {
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, EditNoteFragment.newInstance(notes))
                .addToBackStack(null)
                .commit();
    }
}

