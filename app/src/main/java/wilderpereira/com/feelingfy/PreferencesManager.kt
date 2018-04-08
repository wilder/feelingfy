package wilderpereira.com.feelingfy

import android.content.Context
import android.content.SharedPreferences

/**
 * Manages dealing with SharedPreferences
 * Created by Wilder on 19/02/18.
 */
class PreferencesManager (context: Context) {

    val PREFS_FILENAME = "impactaapp.prefs"
    val MAIN_IMAGE_PATH = "mainimage_key"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var mainImagePath: String
        get() = prefs.getString(MAIN_IMAGE_PATH, "")
        set(value) = prefs.edit().putString(MAIN_IMAGE_PATH, value).apply()

}
