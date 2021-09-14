package com.example.mynotes.domain;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {
    @Override
    public List<Notes> getNotes() {
        ArrayList<Notes> notes = new ArrayList<>();

        notes.add(new Notes("Запись 1", "Текст записи 1 ", "https://cdn.pixabay.com/photo/2021/01/29/08/10/musician-5960112_960_720.jpg", "07.01.2020"));
        notes.add(new Notes("Запись 2", "Текст записи 2 ", "https://cdn.pixabay.com/photo/2021/06/22/14/55/girl-6356393_960_720.jpg", "06.02.2020"));
        notes.add(new Notes("Запись 3", "Текст записи 3 ", "https://cdn.pixabay.com/photo/2021/05/10/10/46/yellow-wall-6243164_960_720.jpg", "05.03.2020"));
        notes.add(new Notes("Запись 4", "Текст записи 4 ", "https://cdn.pixabay.com/photo/2021/07/23/14/52/submarine-6487509_960_720.png", "04.04.2020"));
        notes.add(new Notes("Запись 5", "Текст записи 5 ", "https://cdn.pixabay.com/photo/2019/12/22/01/14/snowman-4711637_960_720.png", "03.05.2020"));
        notes.add(new Notes("Запись 6", "Текст записи 6 ", "https://cdn.pixabay.com/photo/2019/06/13/09/41/business-4271251_960_720.png", "02.06.2020"));
        notes.add(new Notes("Запись 7", "Текст записи 7 ", "https://cdn.pixabay.com/photo/2019/05/21/17/15/dive-4219524_960_720.png", "01.07.2020"));

        return notes;
    }
}
