package com.example.mynotes.domain;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FireStoreNotesRepository implements NotesRepository {

    private static final String NOTES = "notes";
    private static final String NAME = "name";
    private static final String IMAGE_URL = "imageUrl";
    private static final String CREATED_AT = "createdAt";

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void getNotes(Callback<List<Notes>> callback) {
        db.collection(NOTES)
                .orderBy(CREATED_AT, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Notes> result = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.get(NAME, String.class);
                                String imageUrl = document.get(IMAGE_URL, String.class);
                                long timeStamp = document.getDate(CREATED_AT).getTime();

                                result.add(new Notes(document.getId(), name, imageUrl, new Date(timeStamp)));
                            }
                            callback.onSuccess(result);
                        } else {

                        }
                    }
                });


    }

    @Override
    public void addNote(String name, String imageUrl, Callback<Notes> callback) {

        HashMap<String, Object> data = new HashMap<>();

        Date createdAt = new Date();

        data.put(NAME, name);
        data.put(IMAGE_URL, imageUrl);
        data.put(CREATED_AT, createdAt);

        db.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            String noteId = task.getResult().getId();
                            callback.onSuccess(new Notes(noteId, name, imageUrl, createdAt));
                        }

                    }
                });

    }

    @Override
    public void removeNote(Notes notes, Callback<Void> callback) {
        db.collection(NOTES)
                .document(notes.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess(unused);
                    }
                });

    }
}
