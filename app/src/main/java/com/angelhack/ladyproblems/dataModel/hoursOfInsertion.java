package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class hoursOfInsertion {
    private static final String TAG = hoursOfInsertion.class.getSimpleName();

    public enum hours {
        h_lessThan3(0),
        h_greaterThan3(1);

        int value;

        hours(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case h_lessThan3:
                    return "LessThan3";
                case h_greaterThan3:
                    return "GreaterThan3";
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

