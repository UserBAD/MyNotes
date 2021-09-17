package com.example.mynotes.domain;

import java.util.List;

public interface NotesRepository {

    void getNotes(Callback<List<Notes>>callback);

    void addNote(String name, String imageUrl, Callback<Notes> callback);

    void removeNote(Notes notes, Callback<Void> callback);

}
