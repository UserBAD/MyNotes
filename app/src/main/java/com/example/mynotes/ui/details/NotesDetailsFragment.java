package com.example.mynotes.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.mynotes.MainActivity;
import com.example.mynotes.R;
import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.list.NotesListFragment;

public class NotesDetailsFragment extends Fragment {

    private static final String ARG_NOTE = "ARG_NOTE";

    public static NotesDetailsFragment newInstance(Notes notes) {
        NotesDetailsFragment fragment = new NotesDetailsFragment();
        Bundle arguments = new Bundle();

        arguments.putParcelable(ARG_NOTE, notes);

        fragment.setArguments(arguments);
        return fragment;

    }

    private void displayNotes(Notes notes) {
        noteName.setText(notes.getName());
        noteText.setText(notes.getText());
        noteData.setText(notes.getData());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_details, container, false);
    }

    private TextView noteName;
    private TextView noteText;
    private TextView noteData;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteName = view.findViewById(R.id.note_name);
        noteText = view.findViewById(R.id.note_text);
        noteData = view.findViewById(R.id.note_data);

        getParentFragmentManager().setFragmentResultListener(NotesListFragment.KEY_SELECTED_NOTES, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Notes notes = result.getParcelable(NotesListFragment.ARG_NOTE);
                displayNotes(notes);
            }
        });
        if (getArguments() != null && getArguments().containsKey(ARG_NOTE)) {

            Notes notes = getArguments().getParcelable(ARG_NOTE);

            if (notes != null) {
                displayNotes(notes);
            }
        }
    }
}
