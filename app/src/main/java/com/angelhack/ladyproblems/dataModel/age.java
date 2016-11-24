package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/22/16.
 */

public class age {

    private static final String TAG = age.class.getSimpleName();

    private static ages AGES;

    public age(ages ages){
        AGES = ages;
    }

    public static ages getAGES() {
        return AGES;
    }

    public enum ages {
        a_unknown(0),
        a_under18(1),
        a_18t024(2),
        a_25to34(3),
        a_35to44(4),
        a_45to54(5),
        a_55to64(6),
        a_65plus(7);

        int value;

        ages(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case a_unknown:
                    return "Age not calculated";
                case a_under18:
                    return "age: under 18";
                case a_18t024:
                    return "age: 18-24";
                case a_25to34:
                    return "age: 25-34";
                case a_35to44:
                    return "age: 35-44";
                case a_45to54:
                    return "age: 45-54";
                case a_55to64:
                    return "age: 55-64";
                case a_65plus:
                    return "age: 65+";
                default:
                    Log.e(TAG, "Unknown Age");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}
