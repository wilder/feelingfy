package wilderpereira.com.feelingfy.pojo

import java.util.ArrayList;
import java.util.List;

public class PicturesList {

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
}