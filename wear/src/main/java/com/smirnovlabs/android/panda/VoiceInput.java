package com.smirnovlabs.android.panda;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.common.base.Joiner;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Arrays;
import java.util.List;

import static com.smirnovlabs.android.panda.Constants.MUSIC_API_URL;
import static com.smirnovlabs.android.panda.Constants.PANDA_BASE_URL;
import static com.smirnovlabs.android.panda.Constants.PLAY_SONG;

public class VoiceInput extends Activity {

    private TextView mTextView;
    private static final int SPEECH_REQUEST_CODE = 539890;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_input);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
                // add button click listener
                Button voiceButton  = (Button) findViewById(R.id.start_voice_input);
                voiceButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        displaySpeechRecognizer();
                    }
                });
            }
        });

        // DO NOT PUT findViewByID() here - will return null, since context is in fragment

        // Show this on app open, to save us a click.
        displaySpeechRecognizer();
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
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);

            String spokenText = results.get(0);
            System.out.printf("Spoken Text: %s \n", spokenText); // debug TODO remove me in final version
            processCommand(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Given a command, perform relevant API call to Panda on Puma.
     * */
    private void processCommand(String text) {
        String[] tokens = text.split(" ");
        JsonObject data = new JsonObject();

        switch (tokens[0]) {
            case "play":
                String payload = Joiner.on(" ").join(Arrays.copyOfRange(tokens,1, tokens.length));


                System.out.println("payload: " + payload);

                // add to json
                data.addProperty("query", payload);
                performAPICall(PANDA_BASE_URL + MUSIC_API_URL + PLAY_SONG , data);

                break;

            case "next":
                break;

            case "skip":

                break;

            case "previous":
                break;

            case "back":
                break;
        }

    }

    /**
     * Performs async API call to Panda server. Returns result from server as string.
     * */
    private void performAPICall(String url, JsonObject data ) {
        Ion.with(getApplicationContext())
            .load(url)
            .setJsonObjectBody(data)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    // do stuff with the result or error
                    displayResult(result);
                }
            });
    }

    /** Processed returned json from server, displays to user if needed. */
    private void displayResult(JsonObject result) {
        System.out.printf("Returned json: %s \n", result.toString());
        // TODO implement me
    }
}
