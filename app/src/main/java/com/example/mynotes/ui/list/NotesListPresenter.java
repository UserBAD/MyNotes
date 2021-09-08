package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Notes;
import com.example.mynotes.domain.NotesRepository;

import java.util.List;

public class NotesListPresenter {
    private NotesListView view;
    private NotesRepository repository;

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        List<Notes> result = repository.getNotes();

        view.showNotes(result);
    }
}
