package com.smirnovlabs.android.panda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Adapter for displaying commands with an icon and help text.
 */
public class CommandListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] titles;
    private final String[] descriptions;

    public CommandListAdapter(Context context, String[] titles, String[] descriptions) {
        super(context, R.layout.command_row_layout, titles);
        this.context = context;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.command_row_layout, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        firstLine.setText(titles[position]);
        secondLine.setText(descriptions[position]);
        return rowView;
    }
}
