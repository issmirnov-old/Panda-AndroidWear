package com.smirnovlabs.android.panda;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Listens for messages from wearable, perfoms API call to do the command.
 * TODO - this might be deprecated. delete?
 */
public class WearMessageListenerService extends WearableListenerService {
    private static final String START_ACTIVITY = "/panda_communication_activity";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if( messageEvent.getPath().equalsIgnoreCase( START_ACTIVITY ) ) {
            // perform the api call
            // TODO -  extract the url and json string payload, then perform the API call.
            // upon getting the result, send it back in a message, and maybe display a toast on mobile as well.
        } else {
            super.onMessageReceived(messageEvent);
        }
    }


    /**
     * Performs async API call to Panda server. Returns result from server as string.
     * */
    private void performAPICall(String url, JsonObject data ) {
        System.out.printf("performing API call to %s with dataJson: %s: ", url, data.toString());
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
            System.out.printf("Null result. Did you finish the API calls?");
            return;
        }
        System.out.printf("Returned json: %s \n", result.toString());
        // TODO implement me
    }
}
