package com.smirnovlabs.android.common;

/**
 * Constants for Panda Home Automation app.
 */
public class Constants {
    public static final String PANDA_BASE_URL = "http://puma/panda/api";

    // === MUSIC API endpoints
    public static final String MUSIC_API_URL = "/script/music";
    public static final String PLAY_SONG = "/play_song";
    public static final String NEXT_SONG = "/next_song";
    public static final String PREV_SONG = "/prev_song";
    public static final String PAUSE = "/pause";
    public static final String RESUME = "/resume";
    public static final String VOL_UP = "/volume_up";
    public static final String VOL_DOWN = "/volume_down";
    public static final String VOL_SET = "/volume_set";
    public static final String TOP_RATED = "/top_rated";
    public static final String MOST_PLAYED = "/most_played";

    //  === API's for triggering general carbon scripts.
    public static final String CARBON_API_URL = "/script/carbon";
    public static final String DAY_SUMMARY = "/day_summary";

    // Health endpoint, used to check connection ot server.
    public static final String HEALTH = "/health";

    /** Key used for storing aux data in jsons.*/
    public static final String DATA_KEY = "data";
}