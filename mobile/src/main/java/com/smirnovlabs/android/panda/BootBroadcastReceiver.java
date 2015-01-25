package com.smirnovlabs.android.panda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Starts WearMessageListenerService via an intent.
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Filter for boot completed action.
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Intent startServiceIntent = new Intent(context, WearMessageListenerService.class);
            context.startService(startServiceIntent);
        }
    }
}
