package com.smirnovlabs.android.panda;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/** Shows a list of commands. */
public class CommandsFragment extends ListFragment {
    private String TAG = "PANDA command fragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_commands, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] titles = getResources().getStringArray(R.array.titles_array);
        String[] descriptions = getResources().getStringArray(R.array.descriptions_array);

        CommandListAdapter adapter = new CommandListAdapter(getActivity().getApplicationContext(), titles, descriptions);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(getActivity(), item + " selected", Toast.LENGTH_LONG).show();
    }
}