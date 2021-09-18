package com.example.mynotes.ui.list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.domain.DeviceNotesRepository;
import com.example.mynotes.domain.Notes;
import com.example.mynotes.ui.Router;
import com.example.mynotes.ui.edit.EditNoteFragment;

import java.util.List;

public class NotesListFragment extends Fragment implements NotesListView, RouterHolder {


    public static final String KEY_SELECTED_NOTES = "KEY_SELECTED_NOTES";
    public static final String ARG_NOTE = "ARG_NOTE";

    private Router router;

    @Override
    public Router getRouter() {
        return router;
    }

    public interface OnNotesClicked {
        void onNotesOnClicked(Notes notes);
    }

    private NotesListPresenter presenter;
    private NotesAdapter adapter;
    private OnNotesClicked onNotesClicked;
    private ProgressBar progressBar;
    private RecyclerView noteList;
    private Notes selectedNote;
    private boolean wasNotesRequested;
    private AlertDialog dialog;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNotesClicked) {
            onNotesClicked = (OnNotesClicked) context;
        }
        if (context instanceof RouterHolder) {
            router = ((RouterHolder) context).getRouter();
        } else if (getParentFragment() instanceof RouterHolder) {
            router = ((RouterHolder) getParentFragment()).getRouter();
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

        router = new Router(getChildFragmentManager());

        presenter = new NotesListPresenter(this, DeviceNotesRepository.INSTANCE);

        adapter = new NotesAdapter(this);

        adapter.setListener(new NotesAdapter.OnNoteClickedListener() {
            @Override
            public void onNoteClicked(Notes notes) {
                if (onNotesClicked != null) {
                    onNotesClicked.onNotesOnClicked(notes);
                }
                Bundle bundle = new Bundle();
                bundle.putParcelable(ARG_NOTE, notes);
                getParentFragmentManager().setFragmentResult(KEY_SELECTED_NOTES, bundle);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getParentFragmentManager().setFragmentResultListener(EditNoteFragment.KEY_NOTE_RESULT, getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Notes notes = result.getParcelable(EditNoteFragment.ARG_NOTE);
                int index = adapter.updateNote(notes);
                adapter.notifyItemChanged(index);
            }
        });

        adapter.setLongClickedListener(new NotesAdapter.OnNoteLongClickedListener() {
            @Override
            public void onNoteLongClicked(Notes notes) {
                selectedNote = notes;
            }
        });

        progressBar = view.findViewById(R.id.progress);

        noteList = view.findViewById(R.id.notes_list);
        noteList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        noteList.setAdapter(adapter);


        if (!wasNotesRequested) {
            presenter.requestNotes();
            wasNotesRequested = true;
        }

    }

    @Override
    public void showNotes(List<Notes> notes) {

        adapter.setNotes(notes);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onNoteAdded(Notes notes) {
        adapter.addNote(notes);

        adapter.notifyItemInserted(adapter.getItemCount() - 1);

        noteList.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void onNoteRemoved(Notes selectedNote) {
        int index = adapter.removeNote(selectedNote);
        adapter.notifyItemRemoved(index);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.menu_notes_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            showSimpleAlert();
            dialog.show();

            Toast.makeText(requireContext(), "Удалить " + selectedNote.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (item.getItemId() == R.id.action_update) {
            if (router != null) {
                router.showEditNote(selectedNote);
            }
            Toast.makeText(requireContext(), "Изменить " + selectedNote.getName(), Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    private void showSimpleAlert() {
        dialog = new AlertDialog.Builder(requireContext())
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message)
                .setCancelable(false)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.requestNotes(selectedNote);
                    }
                })
                .setNegativeButton(R.string.negative, null)
                .create();
        dialog.show();
    }

}
