package com.smirnovlabs.android.panda.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.JsonObject;
import com.smirnovlabs.android.common.util.AppUtils;
import com.smirnovlabs.android.common.logic.Command;
import com.smirnovlabs.android.panda.CommandListAdapter;
import com.smirnovlabs.android.panda.R;

import java.util.ArrayList;
import java.util.List;

import static com.smirnovlabs.android.common.util.Constants.DATA_KEY;

/** Shows a list of commands. */
public class CommandsFragment extends ListFragment {
    private String TAG = "PANDA command fragment";

    /** Code used to get voice input as text. s*/
    private static final int SPEECH_REQUEST_CODE = 739690;

    /** Handle on command for post speech result. */
    private Command pendingCommand;

    /** Handle on view for post speech result. */
    private View pendingView;


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
            pendingCommand = c;
            pendingView = v;
            displaySpeechRecognizer();
        } else {
            c.callAPI(v.getContext());
        }
    }



    /**
     * Create an intent that can start the Speech Recognizer activity
     * */
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        // Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    /**
     * This callback is invoked when the Speech Recognizer returns.
     * This is where you process the intent and extract the speech text from the intent.
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            JsonObject payload = new JsonObject();
            payload.addProperty(DATA_KEY, spokenText);
            pendingCommand.callAPI(pendingView.getContext(), payload);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}