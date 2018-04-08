package wilderpereira.com.feelingfy.camera

import android.content.Intent
import android.hardware.Camera
import android.hardware.Camera.PictureCallback
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
import android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_presentation_camera.*
import wilderpereira.com.feelingfy.PreferencesManager
import wilderpereira.com.feelingfy.R
import wilderpereira.com.feelingfy.results.DetailedActivity
import wilderpereira.com.feelingfy.results.JoyActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.app.ProgressDialog




class PresentationCameraActivity : AppCompatActivity() {

    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null
    private val TAG = "CameraActivity"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation_camera)

        mCamera = getCameraInstance()

        mPreview = CameraPreview(this, mCamera)
        cameraPreview.addView(mPreview)

    }

    fun finalizePresentation(view: View?) {
        takePicture()

        val dialog = ProgressDialog.show(this@PresentationCameraActivity, "",
                "Processing images", true)

        dialog.show()

        val handler = Handler()
        handler.postDelayed(Runnable {
            dialog.dismiss()
            val intent = Intent(this@PresentationCameraActivity, JoyActivity::class.java)
            startActivity(intent)
        }, 1000)


    }

    fun takePicture() : File? {
        var picture: File? = null
        mCamera?.takePicture(null, null, PictureCallback { data, camera ->
            val pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE)
            if (pictureFile == null) {
                Log.d(TAG, "Error creating media file, check storage permissions")
                return@PictureCallback
            }

            try {
                val fos = FileOutputStream(pictureFile)
                PreferencesManager(baseContext).mainImagePath = pictureFile.absolutePath
                fos.write(data)
                fos.close()
                camera.release()
                //camera.startPreview()
            } catch (e: FileNotFoundException) {
                Log.d(TAG, "File not found: " + e.message)
            } catch (e: IOException) {
                Log.d(TAG, "Error accessing file: " + e.message)
            }
        })
        return picture
    }

    fun getCameraInstance(): Camera? {
        var c: Camera? = null
        try {
            c = Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            Log.e(TAG, e.message)
        }

        return c // returns null if camera is unavailable
    }

    /** Create a file Uri for saving an image or video  */
    private fun getOutputMediaFileUri(type: Int): Uri {
        return Uri.fromFile(getOutputMediaFile(type))
    }

    private fun getOutputMediaFile(type: Int): File? {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "feelingfy")
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory")
                return null
            }
        }

        // Create a media file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val mediaFile: File
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg")
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4")
        } else {
            return null
        }

        return mediaFile
    }
}
