package com.smirnovlabs.android.panda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.smirnovlabs.android.common.logic.Command;

import java.util.ArrayList;

/**
 * Adapter for displaying commands with an icon and help text.
 */
public class CommandListAdapter extends ArrayAdapter<Command> {
    private final Context context;
    private ArrayList<Command> commands;

    public CommandListAdapter(Context context, ArrayList<Command> commands) {
        super(context, R.layout.command_row_layout, commands);
        this.context = context;
        this.commands = commands;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.command_row_layout, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);

        Command c = commands.get(position);
        firstLine.setText(c.getTitle());
        secondLine.setText(c.getDescription());
        return rowView;
    }
}
