package wilderpereira.com.feelingfy.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import wilderpereira.com.feelingfy.pojo.Picture
import wilderpereira.com.feelingfy.pojo.PicturesList
import java.io.File

/**
 * Created by Wilder on 07/04/18.
 */
@Parcelize
data class PresentationItem(
        var title: String?,
        val quality: Float?,
        var image: File?,
        val picture: PicturesList?
) : Parcelable
