package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka, Dhara on 11/20/16.
 */

public class absorbency {

    private static final String TAG = absorbency.class.getSimpleName();

    public enum absorbencies {
        ab_little(0),
        ab_regular(0),
        ab_super(0),
        ab_super_plus(0),
        ab_ultra(0);


        int value;

        absorbencies(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case ab_little:
                    return "Little";
                case ab_regular:
                    return "Regular";
                case ab_super:
                    return "Super";
                case ab_super_plus:
                    return "Super plus";
                case ab_ultra:
                    return "Ultra";
                default:
                    Log.e(TAG, "Unknown Absorbency Level");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}
