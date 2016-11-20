package com.angelhack.ladyproblems.dataModel;

import android.util.Log;

/**
 * Created by Bhavsar, Dhara on 11/20/16.
 */

public class emotion {

    private static final String TAG = emotion.class.getSimpleName();

    public enum emotions {
        e_anger(0),
        e_contempt(1),
        e_disgust(2),
        e_fear(3),
        e_joy(4),
        e_sadness(5),
        e_surprise(6);

        int value;

        emotions(int value) {
            this.value = value;
        }

        public String getText() {
            switch (this) {
                case e_anger:
                    return "Anger";
                case e_contempt:
                    return "Contempt";
                case e_disgust:
                    return "Disgust";
                case e_fear:
                    return "Fear";
                case e_joy:
                    return "Joy";
                case e_sadness:
                    return "Sadness";
                case e_surprise:
                    return "Surprise";
                default:
                    Log.e(TAG, "Unknown Emotion");
                    return null;
            }
        }

        public int getValue() {
            return value;
        }
    }
}
