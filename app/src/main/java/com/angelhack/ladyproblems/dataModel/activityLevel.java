package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by Priyanka, Dhara on 11/20/16.
 */

public class activityLevel {

    private static final String TAG = activityLevel.class.getSimpleName();

    private static activity ACTIVITY;

    public activityLevel(activity activity) {
        ACTIVITY = activity;
    }

    public static activity getACTIVITY() {
        return ACTIVITY;
    }

    public enum activity {
        ac_sleeping(0),
        ac_low_resting(1),
        ac_moderate_sitting(2),
        ac_high_exercise(3);

        int value;

        activity(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case ac_sleeping:
                    return "Sleeping";
                case ac_low_resting:
                    return "Low or Resting";
                case ac_moderate_sitting:
                    return "Moderate or Sitting";
                case ac_high_exercise:
                    return "High or Exercise";
                default:
                    Log.e(TAG, "Unknown Activity Level");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}
