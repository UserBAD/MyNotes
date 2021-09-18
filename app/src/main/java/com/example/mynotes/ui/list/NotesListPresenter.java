package com.example.mynotes.ui.list;

import com.example.mynotes.domain.Callback;
import com.example.mynotes.domain.Notes;
import com.example.mynotes.domain.NotesRepository;

import java.util.ArrayList;
import java.util.List;

public class NotesListPresenter {
    private NotesListView view;
    private NotesRepository repository;
    private ArrayList<Notes> notes = new ArrayList<>();

    public NotesListPresenter(NotesListView view, NotesRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void requestNotes() {
        view.showProgress();
        repository.getNotes(new Callback<List<Notes>>() {
            @Override
            public void onSuccess(List<Notes> data) {
                view.showNotes(data);
                view.hideProgress();
            }
        });
    }

    public void addNote(String name, String imageUrl) {
        view.showProgress();
        repository.addNote(name, imageUrl, new Callback<Notes>() {

            @Override
            public void onSuccess(Notes data) {
                view.hideProgress();
                view.onNoteAdded(data);
            }
        });
    }

    public void requestNotes(Notes selectedNote) {
        view.showProgress();
        repository.removeNote(selectedNote, new Callback<Void>() {

            @Override
            public void onSuccess(Void data) {
                view.hideProgress();
                view.onNoteRemoved(selectedNote);
            }
        });
    }
}


