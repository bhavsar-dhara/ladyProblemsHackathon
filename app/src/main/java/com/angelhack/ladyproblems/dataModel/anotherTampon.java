package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class anotherTampon {
    private static final String TAG = anotherTampon.class.getSimpleName();

    private static tampon TAMPON;

    public anotherTampon(tampon tampon){
        TAMPON = tampon;
    }

    public enum tampon {
        t_yes(0),
        t_no(1);

        int value;

        tampon(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case t_yes:
                    return "Another Tampon";
                case t_no:
                    return "No Tampon";
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

