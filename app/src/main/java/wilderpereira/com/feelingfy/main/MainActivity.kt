package wilderpereira.com.feelingfy.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import wilderpereira.com.feelingfy.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupList()
    }

    fun setupList() {
        val presentationItems = getPresentationItems()

        val sectionAdapter = MainAdapter(presentationItems)

        presentationList.layoutManager = LinearLayoutManager(baseContext) as RecyclerView.LayoutManager?
        presentationList.adapter = sectionAdapter
    }

    private fun getPresentationItems(): List<PresentationItem> {
        val presentationItems = arrayListOf<PresentationItem>()
        for  (i in 1..5) {
            val presentationItem = PresentationItem("Titulo $i", 2.0*i)
            presentationItems.add(presentationItem)
        }
        return presentationItems
    }

}
