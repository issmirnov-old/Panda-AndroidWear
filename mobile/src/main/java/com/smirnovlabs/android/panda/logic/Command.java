package com.smirnovlabs.android.panda.logic;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Logical object for keeping track of command names and descriptions, as well as
 * API endpoints.
 */
public class Command {
    private String TAG = "Command";
    /** Title of command, displayed in list.*/
    private String title;
    /** Description how to use command, */
    private String description;
    /** Target for API call. */
    private String apiEndpoint;

    /** Flag to indicate we need additional info for API call.*/
    private boolean needsPayload;


    public Command(String title, String description, String endpoint, boolean payloadRequired){
        this.title = title;
        this.description = description;
        this.apiEndpoint = endpoint;
        this.needsPayload = payloadRequired;
    }


    /** Sends POST request with json payload.*/
    public void callAPI(Context c, JsonObject payload) {
        Log.d(TAG, "calling api: " + apiEndpoint);
        Ion.with(c)
            .load(apiEndpoint)
            .setJsonObjectBody(payload)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    Log.d(TAG, "e: " + e);
                    Log.d(TAG, "result: " + result);
                }
            });
    }

    /** Overloaded method, performs a POST.*/
    public void callAPI(Context c) {
        Log.d(TAG, "calling api: " + apiEndpoint);
        Ion.with(c)
            .load(apiEndpoint)
            .asJsonObject()
            .setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                    Log.d(TAG, "e: " + e);
                    Log.d(TAG, "result: " + result);
                }
            });
    }


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public boolean isPayloadRequired() {
        return needsPayload;
    }
}
