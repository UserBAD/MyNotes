package com.example.mynotes.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynotes.R;
import com.example.mynotes.domain.DeviceNotesRepository;
import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.details.NotesDetailsActivity;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView {


    public static final String KEY_SELECTED_NOTES = "KEY_SELECTED_NOTES";
    public static final String ARG_NOTE = "ARG_NOTE";

    public interface OnNotesClicked {
        void onNotesOnClicked(Notes notes);
    }


    private NotesListPresenter presenter;
    private LinearLayout container;
    private OnNotesClicked onNotesClicked;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNotesClicked) {
            onNotesClicked = (OnNotesClicked) context;
        }
    }

    @Override
    public void onDetach() {
        onNotesClicked = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NotesListPresenter(this, new DeviceNotesRepository());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        container = view.findViewById(R.id.notes_list);

        presenter.requestNotes();

    }

    @Override
    public void showNotes(List<Notes> notes) {

        for (Notes note : notes) {

            View noteItem = LayoutInflater.from(requireContext()).inflate(R.layout.item_note, container, false);
            noteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onNotesClicked != null) {
                        onNotesClicked.onNotesOnClicked(note);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_NOTE, note);
                    getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTES, bundle);

                }
            });

            TextView noteName = noteItem.findViewById(R.id.note_name);

            noteName.setText(note.getName());

            container.addView(noteItem);
        }
    }
}
