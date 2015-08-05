package com.smirnovlabs.android.common.util;

import com.smirnovlabs.android.common.logic.Command;

import java.util.ArrayList;

/**
 * Util functions for the app.
 */
public class AppUtils {

    /** Returns hard coded list of commands. What's a better way to do this? */
    public static  ArrayList<Command> generateCommands() {
        ArrayList<Command> result = new ArrayList<>();
        result.add(new Command(
                "Play {name of song or artist}",
                "search for song matching the query",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.PLAY_SONG,
                true
        ));
        result.add(new Command(
                "Pause",
                "pause song",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.PAUSE,
                false
        ));
        result.add(new Command(
                "Play",
                "resume playing song",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.RESUME,
                false
        ));
        result.add(new Command(
                "Previous",
                "jump back a track",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.PREV_SONG,
                false
        ));
        result.add(new Command(
                "Next | Skip",
                "skip forward a track",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.NEXT_SONG,
                false
        ));
        result.add(new Command(
                "Volume Up",
                "raise volume by 10%",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.VOL_UP,
                false
        ));
        result.add(new Command(
                "Volume Down",
                "lower volume by 10%",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.VOL_DOWN,
                false
        ));
        result.add(new Command(
                "Set volume [to] x[%]",
                "set volume to arbitrary percentage",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.VOL_SET,
                true
        ));
        result.add(new Command(
                "Tell me about my day",
                "tell carbon to read my day summary",
                Constants.PANDA_BASE_URL + Constants.CARBON_API_URL + Constants.DAY_SUMMARY,
                false
        ));
        result.add(new Command(
                "Play top rated song",
                "plays a random song from top rated list",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.TOP_RATED,
                false
        ));
        result.add(new Command(
                "Play most played song ",
                "plays a random song from frequently played list.",
                Constants.PANDA_BASE_URL + Constants.MUSIC_API_URL + Constants.MOST_PLAYED,
                false
        ));

        return result;
    }
}
