package com.example.mynotes.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynotes.R;
import com.example.mynotes.domain.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {


    private List<Notes> date = new ArrayList<>();
    private OnNoteClickedListener listener;
    private OnNoteLongClickedListener longClickedListener;
    private Fragment fragment;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setNotes(List<Notes> toSet) {
        date.clear();
        date.addAll(toSet);
    }

    public void addNote(Notes notes) {
        date.add(notes);


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public NotesAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);

        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NotesViewHolder holder, int position) {
        Notes notes = date.get(position);

        holder.noteName.setText(notes.getName());
        Glide.with(holder.getImage()).load(notes.getImageUrl()).
                centerCrop().into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public OnNoteClickedListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    public OnNoteLongClickedListener getLongClickedListener() {
        return longClickedListener;
    }

    public void setLongClickedListener(OnNoteLongClickedListener longClickedListener) {
        this.longClickedListener = longClickedListener;
    }

    public int removeNote(Notes selectedNote) {
        for (int i = 0; i < date.size(); i++) {
            if (date.get(i).equals(selectedNote)) {
                date.remove(i);
                return i;
            }
        }
        return -1;
    }

    public int updateNote(Notes notes) {

        for (int i = 0; i < date.size(); i++) {
            if (date.get(i).equals(notes)) {
                date.set(i, notes);
                return i;
            }
        }
        return -1;
    }


    interface OnNoteClickedListener {
        void onNoteClicked(Notes notes);
    }

    interface OnNoteLongClickedListener {
        void onNoteLongClicked(Notes notes);
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        private final TextView noteName;
        private final ImageView image;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (getListener() != null) {
                        getListener().onNoteClicked(date.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemView.showContextMenu();

                    if (getLongClickedListener() != null) {
                        getLongClickedListener().onNoteLongClicked(date.get(getAdapterPosition()));
                    }

                    return true;
                }
            });

            noteName = itemView.findViewById(R.id.note_name);
            image = itemView.findViewById(R.id.note_image);
        }

        public TextView getNoteName() {
            return noteName;
        }

        public ImageView getImage() {
            return image;
        }
    }
}
