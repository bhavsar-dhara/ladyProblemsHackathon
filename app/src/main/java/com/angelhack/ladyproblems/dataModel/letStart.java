package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class letStart {
    private static final String TAG = letStart.class.getSimpleName();

    public enum letStartOptions {
        s_remove(0),
        s_insert(1);

        int value;

        letStartOptions(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case s_remove:
                    return "Remove";
                case s_insert:
                    return "Insert";
                default:
                    Log.e(TAG, "Unknown Option");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}

