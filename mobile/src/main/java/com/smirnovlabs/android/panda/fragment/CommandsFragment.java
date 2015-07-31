package com.smirnovlabs.android.panda.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.smirnovlabs.android.panda.CommandListAdapter;
import com.smirnovlabs.android.panda.R;
import com.smirnovlabs.android.panda.logic.Command;

import java.util.ArrayList;

import static com.smirnovlabs.android.common.Constants.MUSIC_API_URL;
import static com.smirnovlabs.android.common.Constants.PANDA_BASE_URL;
import static com.smirnovlabs.android.common.Constants.PAUSE;
import static com.smirnovlabs.android.common.Constants.PLAY_SONG;
import static com.smirnovlabs.android.common.Constants.RESUME;

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

        CommandListAdapter adapter = new CommandListAdapter(getActivity().getApplicationContext(), generateCommands());
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Command c = (Command) getListAdapter().getItem(position);
        if (c.isPayloadRequired()) {
            Log.d(TAG, "payload required!");
            JsonObject payload = new JsonObject();
            String debug = "Ellie";
            payload.addProperty("query", debug);
            // TODO show voice prompter. or dialog. save into payload.
            c.callAPI(v.getContext(), payload);
        } else {
            Log.d(TAG, "good to go");
            c.callAPI(v.getContext());
        }
        Toast.makeText(getActivity(), c.getTitle() + " selected", Toast.LENGTH_LONG).show();

    }


    /** Returns hard coded list of commands. What's a better way to do this? */
    private ArrayList<Command> generateCommands() {
        ArrayList<Command> result = new ArrayList<>();
        result.add(new Command(
                "Play {name of song or artist}",
                "search for song matching the query",
                PANDA_BASE_URL + MUSIC_API_URL + PLAY_SONG,
                true
                ));
        result.add(new Command(
                "Pause",
                "pause song",
                PANDA_BASE_URL + MUSIC_API_URL + PAUSE,
                false
        ));
        result.add(new Command(
                "Play",
                "resume playing song",
                PANDA_BASE_URL + MUSIC_API_URL + RESUME,
                false
        ));

        return result;
    }
}