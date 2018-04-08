package wilderpereira.com.feelingfy.results

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import wilderpereira.com.feelingfy.R

class JoyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joy)
    }

    fun nextJoy(view: View) {
        startActivity(Intent(this@JoyActivity, DetailedActivity::class.java))
    }
}
