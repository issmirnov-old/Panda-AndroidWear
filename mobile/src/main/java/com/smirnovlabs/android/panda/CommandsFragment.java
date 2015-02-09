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
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_commands, container, false);

        // Initialize the list view.




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
        // do something with the data
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(getActivity(), item + " selected", Toast.LENGTH_LONG).show();
    }


    // TODO - add list of command. List view should work, name of command in bold, params in italic.
}