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

    private JsonParser jsonParser;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if( messageEvent.getPath().equalsIgnoreCase(LONGTERM_ACTIVITY) ) {
            // perform the api call
            // TODO -  extract the url and json string payload, then perform the API call.
            // upon getting the result, send it back in a message, and maybe display a toast on mobile as well.
            String data = new String(messageEvent.getData()); // DO NOT use toString() - will causes errors.

            // TODO may need to run on UI thread.
            Toast.makeText(getBaseContext(), "processing message: " + data,
                    Toast.LENGTH_SHORT).show();

            String url = data.split(DELIM)[0];
            if (!data.split(DELIM)[1].equals("{}")) {
                JsonObject jsonData = jsonParser.parse(data.split(DELIM)[1]).getAsJsonObject(); // crashes here
                Log.d(TAG, "url: " + url + " and json: " + jsonData.toString());
                performAPICall(url, jsonData);
            } else {
                Log.d(TAG, "url: " + url );
                performAPICall(url);
            }
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

    /**
     * Performs async API call to Panda server. Returns result from server as string.
     * */
    private void performAPICall(String url) {
        Ion.with(getApplicationContext())
                .load(url)
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
            System.out.printf("Null result. Did you finish the API calls?");
            return;
        }
        System.out.printf("Returned json: %s \n", result.toString());
        // TODO implement me
    }
}
