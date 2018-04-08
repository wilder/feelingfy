package wilderpereira.com.feelingfy.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class PicturesList implements Parcelable {

    List<Picture> pictures;
    List<Float> angerList = new ArrayList<Float>();
    List<Float> surprisedList= new ArrayList<Float>();
    List<Float> exposedList= new ArrayList<Float>();
    List<Float> blurredList= new ArrayList<Float>();
    List<Float> joyList= new ArrayList<Float>();
    List<Float> sorrowList= new ArrayList<Float>();


    public PicturesList(List<Picture> pictures) {
        this.pictures = pictures;

        for (Picture picture: pictures) {
            this.angerList.add(picture.getAnger());
            this.surprisedList.add(picture.getSurprised());
            this.exposedList.add(picture.getExposed());
            this.blurredList.add(picture.getBlurred());
            this.joyList.add(picture.getJoy());
            this.sorrowList.add(picture.getSorrow());
        }


    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Float> getAngerList() {
        return angerList;
    }

    public void setAngerList(List<Float> angerList) {
        this.angerList = angerList;
    }

    public List<Float> getSurprisedList() {
        return surprisedList;
    }

    public void setSurprisedList(List<Float> surprisedList) {
        this.surprisedList = surprisedList;
    }

    public List<Float> getExposedList() {
        return exposedList;
    }

    public void setExposedList(List<Float> exposedList) {
        this.exposedList = exposedList;
    }

    public List<Float> getBlurredList() {
        return blurredList;
    }

    public void setBlurredList(List<Float> blurredList) {
        this.blurredList = blurredList;
    }

    public List<Float> getJoyList() {
        return joyList;
    }

    public void setJoyList(List<Float> joyList) {
        this.joyList = joyList;
    }

    public List<Float> getSorrowList() {
        return sorrowList;
    }

    public void setSorrowList(List<Float> sorrowList) {
        this.sorrowList = sorrowList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.pictures);
        dest.writeList(this.angerList);
        dest.writeList(this.surprisedList);
        dest.writeList(this.exposedList);
        dest.writeList(this.blurredList);
        dest.writeList(this.joyList);
        dest.writeList(this.sorrowList);
    }

    protected PicturesList(Parcel in) {
        this.pictures = in.createTypedArrayList(Picture.CREATOR);
        this.angerList = new ArrayList<Float>();
        in.readList(this.angerList, Float.class.getClassLoader());
        this.surprisedList = new ArrayList<Float>();
        in.readList(this.surprisedList, Float.class.getClassLoader());
        this.exposedList = new ArrayList<Float>();
        in.readList(this.exposedList, Float.class.getClassLoader());
        this.blurredList = new ArrayList<Float>();
        in.readList(this.blurredList, Float.class.getClassLoader());
        this.joyList = new ArrayList<Float>();
        in.readList(this.joyList, Float.class.getClassLoader());
        this.sorrowList = new ArrayList<Float>();
        in.readList(this.sorrowList, Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<PicturesList> CREATOR = new Parcelable.Creator<PicturesList>() {
        @Override
        public PicturesList createFromParcel(Parcel source) {
            return new PicturesList(source);
        }

        @Override
        public PicturesList[] newArray(int size) {
            return new PicturesList[size];
        }
    };
}