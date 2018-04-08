package wilderpereira.com.feelingfy.utils

import com.google.gson.Gson;
import com.mycompany.app.pojo.Picture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum JsonImageSerializer {

    INSTANCE;
    private Gson gson = new Gson();

    public List<Picture> getPictures(String json){
        Picture[] pictures = gson.fromJson(json, Picture[].class);
        return Arrays.asList(pictures);
    }

}