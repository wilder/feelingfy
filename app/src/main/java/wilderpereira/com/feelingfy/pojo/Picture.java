package wilderpereira.com.feelingfy.pojo

import com.google.gson.annotations.SerializedName;

public class Picture {
    @SerializedName("Headwear")
    float headwear;
    @SerializedName("Joy")
    float joy;
    @SerializedName("Sorrow")
    float sorrow;
    @SerializedName("Anger")
    float anger;
    @SerializedName("Surprised")
    float surprised;
    @SerializedName("Exposed")
    float exposed;
    @SerializedName("Blurred")
    float blurred;
    @SerializedName("TimeStamp")
    long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getHeadwear() {
        return headwear;
    }

    public void setHeadwear(int headwear) {
        this.headwear = headwear;
    }

    public float getJoy() {
        return joy;
    }

    public void setJoy(int joy) {
        this.joy = joy;
    }

    public float getSorrow() {
        return sorrow;
    }

    public void setSorrow(int sorrow) {
        this.sorrow = sorrow;
    }

    public float getAnger() {
        return anger;
    }

    public void setAnger(int anger) {
        this.anger = anger;
    }

    public float getSurprised() {
        return surprised;
    }

    public void setSurprised(int surprised) {
        this.surprised = surprised;
    }

    public float getExposed() {
        return exposed;
    }

    public void setExposed(int exposed) {
        this.exposed = exposed;
    }

    public float getBlurred() {
        return blurred;
    }

    public void setBlurred(int blurred) {
        this.blurred = blurred;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "headwear=" + headwear +
                ", joy=" + joy +
                ", sorrow=" + sorrow +
                ", anger=" + anger +
                ", surprised=" + surprised +
                ", exposed=" + exposed +
                ", blurred=" + blurred +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
