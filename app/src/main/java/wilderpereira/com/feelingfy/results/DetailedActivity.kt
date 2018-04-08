package wilderpereira.com.feelingfy.results

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
import wilderpereira.com.feelingfy.PreferencesManager
import wilderpereira.com.feelingfy.R
import wilderpereira.com.feelingfy.pojo.Picture


class DetailedActivity : AppCompatActivity() {

    var averagePicture: Picture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        averagePicture =
                Picture(40.3f,
                61.4f, 55.5f, 74.6f, 56.2f, 85.3f, 35.8f, 1000)
        setupGraph()
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
