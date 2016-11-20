package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by Bhavsar, Dhara on 11/20/16.
 */

public class flow {

    private static final String TAG = flow.class.getSimpleName();

    public enum flows {
        f_increase(0),
        f_same_as_before(1),
        f_decrease(2);

        int value;

        flows(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case f_decrease:
                    return "Decrease";
                case f_increase:
                    return "Increase";
                case f_same_as_before:
                    return "Same as before";
                default:
                    Log.e(TAG, "Unknown Flow");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}
