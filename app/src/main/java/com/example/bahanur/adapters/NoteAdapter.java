package com.example.bahanur.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bahanur.model.Notes;
import com.example.bahanur.twopdoist.R;

import java.util.List;

/**
 * Created by yoda on 2.5.2015.
 */
public class NoteAdapter extends ArrayAdapter<Notes> {
    public NoteAdapter(Context context, List<Notes> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Notes note = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list_row, parent, false);
        }
        // Lookup view for data population
        TextView noteTitle = (TextView) convertView.findViewById(R.id.itemNoteTitle);
        TextView noteDesc =(TextView) convertView.findViewById(R.id.itemNoteDesc);

        noteTitle.setText(note.getTitle());
        noteDesc.setText(note.getIcerik());
        // Return the completed view to render on screen
        return convertView;
    }
}
