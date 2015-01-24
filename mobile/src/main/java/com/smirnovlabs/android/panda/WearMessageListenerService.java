package com.smirnovlabs.android.panda;

import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Listens for messages from wearable, perfoms API call to do the command.
 * TODO - this might be deprecated. delete?
 */
public class WearMessageListenerService extends WearableListenerService {
    private static final String LONGTERM_ACTIVITY = "/panda_service";

    private final String DELIM = "#";
    private String TAG = "PANDA SERVICE";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if( messageEvent.getPath().equalsIgnoreCase(LONGTERM_ACTIVITY) ) {
            JsonParser jsonParser = new JsonParser();
            String data = new String(messageEvent.getData()); // DO NOT use toString() - will causes errors.

            // TODO delete me?
            Toast.makeText(getBaseContext(), "processing message: " + data,
                    Toast.LENGTH_SHORT).show();

            String url = data.split(DELIM)[0];
            JsonObject jsonData = jsonParser.parse(data.split(DELIM)[1]).getAsJsonObject();
            Log.d(TAG, "url: " + url + " and json: " + jsonData.toString());
            performAPICall(url, jsonData);
            // TODO - upon getting the result, send it back in a message, and maybe display a toast on mobile as well.
        } else {
            super.onMessageReceived(messageEvent);
        }
    }


    /**
     * Performs async POST API call to Panda server. Returns result from server as string.
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
        if (result == null) {
            Log.d(TAG, "Null result. Did you finish the API calls?");
            return;
        }
        Log.d(TAG, ("Returned json: " + result.toString()));
        // TODO implement me
    }
}
