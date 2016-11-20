package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class discomfort {
    private static final String TAG = discomfort.class.getSimpleName();

    private static options OPTIONS;

    public discomfort(options options){
        OPTIONS = options;
    }

    public static options getOPTIONS() {
        return OPTIONS;
    }

    public enum options {
        d_yes(0),
        d_no(1);

        int value;

        options(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case d_yes:
                    return "Discomfort";
                case d_no:
                    return "Comfort";
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

