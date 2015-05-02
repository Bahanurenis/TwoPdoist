package com.example.bahanur.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bahanur.model.NoteCategory;
import com.example.bahanur.twopdoist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoda on 2.5.2015.
 */
public class NoteAdapter extends ArrayAdapter<NoteCategory> {
    public NoteAdapter(Context context, List<NoteCategory> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NoteCategory note = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list_row, parent, false);
        }
        // Lookup view for data population
        TextView rowName = (TextView) convertView.findViewById(R.id.row);

        rowName.setText(note.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
