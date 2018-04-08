package wilderpereira.com.feelingfy.pojo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Picture (
    @SerializedName("Headwear")
    var headwear: Float?,
    @SerializedName("Joy")
    var joy: Float?,
    @SerializedName("Sorrow")
    var sorrow: Float? , 
    @SerializedName("Anger")
    var anger: Float? , 
    @SerializedName("Surprised")
    var surprised: Float? , 
    @SerializedName("Exposed")
    var exposed: Float? , 
    @SerializedName("Blurred")
    var blurred: Float? , 
    @SerializedName("TimeStamp")
    var timeStamp: Long?
) : Parcelable {

    fun getQuality(): Float?{
        var overall = 0f
        overall += anger!!
        overall += blurred!!
        overall += exposed!!
        overall += surprised!!
        overall += joy!!
        overall += sorrow!!

        return overall / 6f
    }
}