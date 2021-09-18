package com.example.mynotes.domain;

import android.os.Handler;
import android.os.Looper;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.LogRecord;

public class DeviceNotesRepository implements NotesRepository {

    public static final NotesRepository INSTANCE = new FireStoreNotesRepository();

    private Handler handler = new Handler(Looper.getMainLooper());

    private final  ArrayList<Notes> notes = new ArrayList<>();

    public DeviceNotesRepository(){
        notes.add(new Notes("i1","Запись 1",  "https://cdn.pixabay.com/photo/2021/01/29/08/10/musician-5960112_960_720.jpg",new Date()));
        notes.add(new Notes("i2","Запись 2",  "https://cdn.pixabay.com/photo/2021/06/22/14/55/girl-6356393_960_720.jpg",new Date()));
        notes.add(new Notes("i3","Запись 3",  "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg",new Date()));
        notes.add(new Notes("i4","Запись 4",  "https://cdn.pixabay.com/photo/2021/07/23/14/52/submarine-6487509_960_720.png",new Date()));
        notes.add(new Notes("i5","Запись 5",  "https://cdn.pixabay.com/photo/2019/12/22/01/14/snowman-4711637_960_720.png",new Date()));
        notes.add(new Notes("i6","Запись 6",  "https://cdn.pixabay.com/photo/2019/06/13/09/41/business-4271251_960_720.png",new Date()));
        notes.add(new Notes("i7","Запись 7",  "https://cdn.pixabay.com/photo/2019/05/21/17/15/dive-4219524_960_720.png",new Date()));
    }

    @Override
    public void getNotes(Callback <List<Notes>> callback) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(3000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(notes);
                    }
                });
            }
        }).start();
    }

    @Override
    public void addNote(String name, String imageUrl, Callback<Notes> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(2000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                Notes result;
                result = new Notes(UUID.randomUUID().toString(),name,imageUrl,new Date());

                notes.add(result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(result);
                    }
                });
            }
        }).start();
    }

    @Override
    public void removeNote(Notes notes, Callback<Void> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000L);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                notes.remove(notes);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(null);
                    }
                });
            }
        }).start();
    }
}
