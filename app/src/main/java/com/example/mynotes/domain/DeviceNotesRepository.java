package com.example.mynotes.domain;

import com.example.mynotes.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceNotesRepository implements NotesRepository {
    @Override
    public List<Notes> getNotes() {
        ArrayList<Notes> notes = new ArrayList<>();

        notes.add(new Notes("Запись 1", "Текст записи 1 ", "07.01.2020"));
        notes.add(new Notes("Запись 2", "Текст записи 2 ", "06.02.2020"));
        notes.add(new Notes("Запись 3", "Текст записи 3 ", "05.03.2020"));
        notes.add(new Notes("Запись 4", "Текст записи 4 ", "04.04.2020"));
        notes.add(new Notes("Запись 5", "Текст записи 5 ", "03.05.2020"));
        notes.add(new Notes("Запись 6", "Текст записи 6 ", "02.06.2020"));
        notes.add(new Notes("Запись 7", "Текст записи 7 ", "01.07.2020"));

        return notes;
    }
}
