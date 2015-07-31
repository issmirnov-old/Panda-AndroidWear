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

import static com.smirnovlabs.android.common.Constants.DAY_SUMMARY;
import static com.smirnovlabs.android.common.Constants.MOST_PLAYED;
import static com.smirnovlabs.android.common.Constants.MUSIC_API_URL;
import static com.smirnovlabs.android.common.Constants.NEXT_SONG;
import static com.smirnovlabs.android.common.Constants.PANDA_BASE_URL;
import static com.smirnovlabs.android.common.Constants.PAUSE;
import static com.smirnovlabs.android.common.Constants.PLAY_SONG;
import static com.smirnovlabs.android.common.Constants.PREV_SONG;
import static com.smirnovlabs.android.common.Constants.RESUME;
import static com.smirnovlabs.android.common.Constants.TOP_RATED;
import static com.smirnovlabs.android.common.Constants.VOL_DOWN;
import static com.smirnovlabs.android.common.Constants.VOL_SET;
import static com.smirnovlabs.android.common.Constants.VOL_UP;
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
            payload.addProperty(DATA_KEY, debug);
            // TODO show voice prompter. or dialog. save into payload.
            c.callAPI(v.getContext(), payload);
        } else {
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
        result.add(new Command(
                "Previous",
                "jump back a track",
                PANDA_BASE_URL + MUSIC_API_URL + PREV_SONG,
                false
        ));
        result.add(new Command(
                "Next | Skip",
                "skip forward a track",
                PANDA_BASE_URL + MUSIC_API_URL + NEXT_SONG,
                false
        ));
        result.add(new Command(
                "Volume Up",
                "raise volume by 10%",
                PANDA_BASE_URL + MUSIC_API_URL + VOL_UP,
                false
        ));
        result.add(new Command(
                "Volume Down",
                "lower volume by 10%",
                PANDA_BASE_URL + MUSIC_API_URL + VOL_DOWN,
                false
        ));
        result.add(new Command(
                "Set volume [to] x[%]",
                "set volume to arbitrary percentage",
                PANDA_BASE_URL + MUSIC_API_URL + VOL_SET,
                true
        ));
        result.add(new Command(
                "Tell me about my day",
                "tell carbon to read my day summary",
                PANDA_BASE_URL + MUSIC_API_URL + DAY_SUMMARY,
                false
        ));
        result.add(new Command(
                "Play top rated song",
                "plays a random song from top rated list",
                PANDA_BASE_URL + MUSIC_API_URL + TOP_RATED,
                false
        ));
        result.add(new Command(
                "Play most played song ",
                "plays a random song from frequently played list.",
                PANDA_BASE_URL + MUSIC_API_URL + MOST_PLAYED,
                false
        ));

        return result;
    }
}