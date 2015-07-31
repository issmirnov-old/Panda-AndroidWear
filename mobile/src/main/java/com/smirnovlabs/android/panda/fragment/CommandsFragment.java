package com.smirnovlabs.android.panda.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.smirnovlabs.android.common.AppUtils;
import com.smirnovlabs.android.common.logic.Command;
import com.smirnovlabs.android.panda.CommandListAdapter;
import com.smirnovlabs.android.panda.R;

import java.util.ArrayList;

import static com.smirnovlabs.android.common.Constants.DATA_KEY;

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
        ArrayList<Command> commands = AppUtils.generateCommands();
        CommandListAdapter adapter = new CommandListAdapter(getActivity().getApplicationContext(), commands);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Command c = (Command) getListAdapter().getItem(position);
        if (c.isPayloadRequired()) {
            Log.d(TAG, "payload required!");
            JsonObject payload = new JsonObject();
            String debug = "Ellie";
            payload.addProperty(DATA_KEY, debug);
            // TODO show voice prompter. or dialog. save into payload.
            c.callAPI(v.getContext(), payload);
        } else {
            c.callAPI(v.getContext());
        }
        //Toast.makeText(getActivity(), c.getTitle() + " selected", Toast.LENGTH_LONG).show();
    }
}