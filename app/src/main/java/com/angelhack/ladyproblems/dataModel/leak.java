package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class leak {
    private static final String TAG = leak.class.getSimpleName();

    public enum leakage {
        l_yes(0),
        l_no(1);

        int value;

        leakage(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case l_yes:
                    return "Leakage";
                case l_no:
                    return "NoLeakage";
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

