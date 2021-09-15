package com.example.mynotes.ui.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mynotes.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDetailFragment extends Fragment {


    public NoteDetailFragment() {
        // Required empty public constructor
    }

    public static NoteDetailFragment newInstance(String param1, String param2) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false);
    }
}