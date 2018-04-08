package wilderpereira.com.feelingfy.main

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Wilder on 07/04/18.
 */
@Parcelize
data class PresentationItem(
        var title: String?,
        val quality: Double?
) : Parcelable
