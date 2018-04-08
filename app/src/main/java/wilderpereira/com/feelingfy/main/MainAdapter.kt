package wilderpereira.com.feelingfy.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.presentation_item.view.*
import wilderpereira.com.feelingfy.R
import wilderpereira.com.feelingfy.results.PerTimeAnalysisActivity

/**
 * Created by Wilder on 07/04/18.
 */
class MainAdapter(var presentationItem: List<PresentationItem>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(presentationItem[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.presentation_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return presentationItem.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(presentationItem: PresentationItem) {
            with (presentationItem) {

                itemView.presentationTitle.text = presentationItem.title

                //itemView.ivPresentationMainImage.image
                itemView.qualityPb.progress = presentationItem.quality!!.toInt() * 10

                if (image != null && image!!.exists()) {
                    itemView.ivPresentationMainImage.setImageURI(Uri.fromFile(image))
                }

                itemView.presentationItemContainer.setOnClickListener {
                    val intent = Intent(itemView.context, PerTimeAnalysisActivity::class.java)
                    val bundle = Bundle()
                    bundle.putParcelable("presentationInfo", presentationItem)
                    intent.putExtras(bundle)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }

}