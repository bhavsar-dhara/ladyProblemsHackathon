package com.angelhack.ladyproblems.dataModel;

/**
 * Created by BHPriyanka on 11/20/2016.
 */

public class finalResult {
    private static final String TAG = finalResult.class.getSimpleName();

    private static finalResult mInstance;

    public static finalResult getInstance() {
        if(mInstance == null) {
            mInstance = new finalResult();
        }
        return mInstance;
    }

    private absorbency absorbency;
    private activityLevel activityLevel;
    private anotherTampon anotherTampon;
    private discomfort discomfort;
    private emotion emotion;
    private flow flow;
    private hoursOfInsertion hoursOfInsertion;
    private leak leak;
    private letStart letStart;
    private age age;
    private double smileDegree;

    public com.angelhack.ladyproblems.dataModel.absorbency getAbsorbency() {
        return absorbency;
    }

    public void setAbsorbency(com.angelhack.ladyproblems.dataModel.absorbency absorbency) {
        this.absorbency = absorbency;
    }

    public com.angelhack.ladyproblems.dataModel.activityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(com.angelhack.ladyproblems.dataModel.activityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public com.angelhack.ladyproblems.dataModel.anotherTampon getAnotherTampon() {
        return anotherTampon;
    }

    public void setAnotherTampon(com.angelhack.ladyproblems.dataModel.anotherTampon anotherTampon) {
        this.anotherTampon = anotherTampon;
    }

    public com.angelhack.ladyproblems.dataModel.discomfort getDiscomfort() {
        return discomfort;
    }

    public void setDiscomfort(com.angelhack.ladyproblems.dataModel.discomfort discomfort) {
        this.discomfort = discomfort;
    }

    public com.angelhack.ladyproblems.dataModel.emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(com.angelhack.ladyproblems.dataModel.emotion emotion) {
        this.emotion = emotion;
    }

    public com.angelhack.ladyproblems.dataModel.flow getFlow() {
        return flow;
    }

    public void setFlow(com.angelhack.ladyproblems.dataModel.flow flow) {
        this.flow = flow;
    }

    public com.angelhack.ladyproblems.dataModel.hoursOfInsertion getHoursOfInsertion() {
        return hoursOfInsertion;
    }

    public void setHoursOfInsertion(com.angelhack.ladyproblems.dataModel.hoursOfInsertion hoursOfInsertion) {
        this.hoursOfInsertion = hoursOfInsertion;
    }

    public com.angelhack.ladyproblems.dataModel.leak getLeak() {
        return leak;
    }

    public void setLeak(com.angelhack.ladyproblems.dataModel.leak leak) {
        this.leak = leak;
    }

    public com.angelhack.ladyproblems.dataModel.letStart getLetStart() {
        return letStart;
    }

    public void setLetStart(com.angelhack.ladyproblems.dataModel.letStart letStart) {
        this.letStart = letStart;
    }

    public age getAge() {
        return age;
    }

    public void setAge(age age) {
        this.age = age;
    }


    public double getSmileDegree() {
        return smileDegree;
    }

    public void setSmileDegree(double smileDegree) {
        this.smileDegree = smileDegree;
    }
}
