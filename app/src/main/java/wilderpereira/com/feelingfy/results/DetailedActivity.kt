package wilderpereira.com.feelingfy.results

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet
import kotlinx.android.synthetic.main.activity_detailed.*
import wilderpereira.com.feelingfy.R
import wilderpereira.com.feelingfy.main.PresentationItem
import wilderpereira.com.feelingfy.pojo.Picture
import wilderpereira.com.feelingfy.pojo.PicturesList
import wilderpereira.com.feelingfy.utils.JsonImageSerializer
import wilderpereira.com.feelingfy.utils.PictureUtils


class DetailedActivity : AppCompatActivity() {

    var averagePicture: Picture? = null
    private lateinit var picturesList: PicturesList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        picturesList = getPicturesList()
        averagePicture = PictureUtils.getMean(picturesList.pictures)

        setupGraph()
    }

    fun getPicturesList(): PicturesList {
        val jsonInString = "[\n" +
                "    {\n" +
                "        \"Headwear\": 0.0,\n" +
                "        \"Joy\": 0.73,\n" +
                "        \"Sorrow\": 0.1,\n" +
                "        \"Anger\": 0.1,\n" +
                "        \"Surprised\": 0.3,\n" +
                "        \"Exposed\": 0.1,\n" +
                "        \"Blurred\": 0.13\n" +
                "    },\n" +
                "    {\n" +
                "        \"Headwear\": 0.1,\n" +
                "        \"Joy\": 0.7,\n" +
                "        \"Sorrow\": 0.2,\n" +
                "        \"Anger\": 0.17,\n" +
                "        \"Surprised\": 0.5,\n" +
                "        \"Exposed\": 0.0,\n" +
                "        \"Blurred\": 0.17\n" +
                "    },\n" +
                "    {\n" +
                "        \"Headwear\": 0.1,\n" +
                "        \"Joy\": 0.63,\n" +
                "        \"Sorrow\": 0.15,\n" +
                "        \"Anger\": 0.2,\n" +
                "        \"Surprised\": 0.52,\n" +
                "        \"Exposed\": 0.02,\n" +
                "        \"Blurred\": 0.2\n" +
                "    },\n" +
                "    {\n" +
                "        \"Headwear\": 0.1,\n" +
                "        \"Joy\": 0.7,\n" +
                "        \"Sorrow\": 0.12,\n" +
                "        \"Anger\": 0.2,\n" +
                "        \"Surprised\": 0.5,\n" +
                "        \"Exposed\": 0.1,\n" +
                "        \"Blurred\": 0.17\n" +
                "    },\n" +
                "    {\n" +
                "        \"Headwear\": 0.1,\n" +
                "        \"Joy\": 0.77,\n" +
                "        \"Sorrow\": 0.15,\n" +
                "        \"Anger\": 0.2,\n" +
                "        \"Surprised\": 0.7,\n" +
                "        \"Exposed\": 0.4,\n" +
                "        \"Blurred\": 0.14\n" +
                "    }\n" +
                "]"


        val pictures = JsonImageSerializer.INSTANCE.getPictures(jsonInString)
        return PicturesList(pictures)

    }

    fun nextClick(view: View?) {
        val intent = Intent(this@DetailedActivity, PerTimeAnalysisActivity::class.java)
        val presentationItem = PresentationItem(presententionTitleEt.text.toString(),  averagePicture!!.getQuality(), null, picturesList)
        intent.putExtra("presentationItem", presentationItem)
        startActivity(intent)
    }

    fun setupGraph() {

        setData(radarChart)

        radarChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad)

        val xAxis = radarChart.getXAxis()
        xAxis.setTextSize(9f)
        xAxis.setYOffset(0f)
        xAxis.setXOffset(0f)
        xAxis.valueFormatter = object : IAxisValueFormatter {

            private val mActivities = arrayOf("Anger", "Blurred", "Joy", "Sorrow", "Surprised", "Exposed")

            override fun getFormattedValue(value: Float, axis: AxisBase): String {
                return mActivities[value.toInt() % mActivities.size]
            }
        }
        xAxis.textColor = Color.WHITE

        val yAxis = radarChart.getYAxis()
        yAxis.setLabelCount(5, false)
        yAxis.textSize = 9f
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 80f
        yAxis.setDrawLabels(false)

        val l = radarChart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.xEntrySpace = 7f
        l.yEntrySpace = 5f
        l.textColor = Color.WHITE
    }

    fun setData(mChart: RadarChart) {
        val entries1 = ArrayList<RadarEntry>()
        entries1.add(RadarEntry(averagePicture!!.anger!!))
        entries1.add(RadarEntry(averagePicture!!.blurred!!))
        entries1.add(RadarEntry(averagePicture!!.joy!!))
        entries1.add(RadarEntry(averagePicture!!.sorrow!!))
        entries1.add(RadarEntry(averagePicture!!.surprised!!))
        entries1.add(RadarEntry(averagePicture!!.exposed!!))


        val set1 = RadarDataSet(entries1, "Average")
        set1.color = Color.rgb(103, 110, 129)
        set1.fillColor = Color.rgb(103, 110, 129)
        set1.setDrawFilled(true)
        set1.fillAlpha = 180
        set1.lineWidth = 2f
        set1.isDrawHighlightCircleEnabled = true
        set1.setDrawHighlightIndicators(false)

        val sets = ArrayList<IRadarDataSet>()
        sets.add(set1)

        val data = RadarData(sets)
//        data.setValueTypeface(mTfLight)
        data.setValueTextSize(8f)
        data.setDrawValues(false)
        data.setValueTextColor(Color.WHITE)

        mChart.setData(data)
        mChart.invalidate()
    }
}
