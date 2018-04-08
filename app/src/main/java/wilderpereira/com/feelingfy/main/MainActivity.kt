package wilderpereira.com.feelingfy.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import wilderpereira.com.feelingfy.PreferencesManager
import wilderpereira.com.feelingfy.R
import wilderpereira.com.feelingfy.camera.PresentationCameraActivity
import java.io.File

class MainActivity : AppCompatActivity() {

    var presentantionItem: PresentationItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (intent.extras!= null && intent.extras.containsKey("newPresentation")) {
            presentantionItem = intent.extras.get("newPresentation") as PresentationItem
        }

        setupList(presentantionItem)
    }

    public fun startPresentation(view: View?) {
        startActivity(Intent(this@MainActivity, PresentationCameraActivity::class.java))
    }

    fun setupList(presentantionItem: PresentationItem?) {
        val presentationItems = getPresentationItems(presentantionItem)

        val sectionAdapter = MainAdapter(presentationItems)

        presentationList.layoutManager = LinearLayoutManager(baseContext) as RecyclerView.LayoutManager?
        presentationList.adapter = sectionAdapter
    }

    private fun getPresentationItems(presentantionItemMain: PresentationItem?): List<PresentationItem> {
        val presentationItems = arrayListOf<PresentationItem>()
        val presentationItem = PresentationItem("Presentation test", 2.0f, null, null)
        presentationItems.add(presentationItem)
        if (presentantionItemMain != null) {
            presentantionItemMain.image = File(PreferencesManager(baseContext).mainImagePath)
            presentationItems.add(presentantionItemMain)
        }
        return presentationItems
    }

}
