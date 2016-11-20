package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka, Dhara on 11/20/16.
 */

public class absorbency {

    private static final String TAG = absorbency.class.getSimpleName();

    private static absorbencies ABSORBENCY_OPTION;

    public absorbency(absorbencies absorbencies) {
        ABSORBENCY_OPTION = absorbencies;
    }

    public enum absorbencies {
        ab_lite(0),
        ab_regular(1),
        ab_super(2),
        ab_super_plus(3),
        ab_ultra(4);


        int value;

        absorbencies(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case ab_lite:
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
