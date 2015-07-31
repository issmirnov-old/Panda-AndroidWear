package com.smirnovlabs.android.common;

import com.smirnovlabs.android.common.logic.Command;

import java.util.ArrayList;

import static com.smirnovlabs.android.common.Constants.CARBON_API_URL;
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

/**
 * Created by vania on 7/30/15.
 */
public class AppUtils {

    /** Returns hard coded list of commands. What's a better way to do this? */
    public static  ArrayList<Command> generateCommands() {
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
                PANDA_BASE_URL + CARBON_API_URL + DAY_SUMMARY,
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
