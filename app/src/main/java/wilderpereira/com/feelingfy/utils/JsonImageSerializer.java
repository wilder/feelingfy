package wilderpereira.com.feelingfy.utils;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import wilderpereira.com.feelingfy.pojo.Picture;

public enum JsonImageSerializer {

    INSTANCE;
    private Gson gson = new Gson();

    public List<Picture> getPictures(String json){
        Picture[] pictures = gson.fromJson(json, Picture[].class);
        return Arrays.asList(pictures);
    }

}