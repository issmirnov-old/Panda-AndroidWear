package com.smirnovlabs.android.panda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * TODO add description
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
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);


        firstLine.setText(titles[position]);
        secondLine.setText(descriptions[position]);

        /*
        // change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("iPhone")) {
            imageView.setImageResource(no);
        } else {
            imageView.setImageResource(ok);
        }
        */
        return rowView;
    }
}
