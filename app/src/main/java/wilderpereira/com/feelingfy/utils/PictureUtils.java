package wilderpereira.com.feelingfy.utils
import com.mycompany.app.pojo.Picture;

import java.util.List;

public class PictureUtils {
    public static Picture getMean(List<Picture> pictures){
        float meanAnger = 0;
        float meanJoy = 0;
        float meanSorrow = 0;
        float meanSurprised = 0;
        float meanExposed = 0;
        float meanBlurred = 0;

        for(Picture picture: pictures){
            meanAnger += picture.getAnger();
            meanJoy += picture.getJoy();
            meanSorrow += picture.getSorrow();
            meanSurprised += picture.getSurprised();
            meanExposed += picture.getExposed();
            meanBlurred += picture.getBlurred();
        }

        Picture meanPicture = new Picture();
        meanPicture.setAnger(meanAnger);
        meanPicture.setJoy(meanJoy);
        meanPicture.setSorrow(meanSorrow);
        meanPicture.setSurprised(meanSurprised);
        meanPicture.setExposed(meanExposed);
        meanPicture.setBlurred(meanBlurred);

        return meanPicture;
    }
}
