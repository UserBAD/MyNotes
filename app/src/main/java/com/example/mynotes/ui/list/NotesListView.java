package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Notes;

import java.util.List;

public interface NotesListView {
    void showNotes(List<Notes> notes);

    void showProgress();

    void hideProgress();

    void onNoteAdded(Notes notes);

    void onNoteRemoved(Notes selectedNote);
}
