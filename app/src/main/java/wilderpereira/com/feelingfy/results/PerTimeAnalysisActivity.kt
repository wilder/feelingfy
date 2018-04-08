package wilderpereira.com.feelingfy.results

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import wilderpereira.com.feelingfy.R
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import android.graphics.Color.LTGRAY
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.Chart
import kotlinx.android.synthetic.main.activity_per_time_analysis.*
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import wilderpereira.com.feelingfy.pojo.PicturesList
import wilderpereira.com.feelingfy.utils.JsonImageSerializer






class PerTimeAnalysisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_per_time_analysis)

//        val presententionItem = intent.extras.get("presentationItem")
//        Log.d("presentationItem", presententionItem.toString())

        setupChart()
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

    fun setupChart() {

        lineChart.getDescription().setEnabled(false)
        lineChart.setDragDecelerationFrictionCoef(0.9f)

        // enable scaling and dragging
        lineChart.setScaleEnabled(true)
        lineChart.setDrawGridBackground(false)
        lineChart.setHighlightPerDragEnabled(true)

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(true)

        // set an alternative background color
        lineChart.setBackgroundColor(Color.TRANSPARENT)

        // add data
        setData(getPicturesList())

        lineChart.animateX(500)

        // get the legend (only possible after setting data)
        val l = lineChart.getLegend()

        // modify the legend ...
        l.setForm(LegendForm.LINE)
//        l.setTypeface(mTfLight)
        l.setTextSize(11f)
        l.setTextColor(Color.WHITE)
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT)
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        l.setDrawInside(false)
//        l.setYOffset(11f);

        val xAxis = lineChart.getXAxis()
        xAxis.setTextSize(11f)
        xAxis.setTextColor(Color.WHITE)
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        val leftAxis = lineChart.getAxisLeft()
        leftAxis.setTextColor(ColorTemplate.getHoloBlue())
        leftAxis.setAxisMaximum(100f)
        leftAxis.setAxisMinimum(0f)
        leftAxis.setDrawGridLines(true)
        leftAxis.setGranularityEnabled(true)

    }

    fun setYValue(yValue: ArrayList<Entry>, values: List<Float>) {
        for (i in 0 until values.size) {
            yValue.add(Entry(i.toFloat(), values[i]))
        }
    }

    fun setData(picturesList: PicturesList) {
        //ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        val yVals1 = ArrayList<Entry>()
        val yVals2 = ArrayList<Entry>()
        val yVals3 = ArrayList<Entry>()
        val yVals4 = ArrayList<Entry>()
        val yVals5 = ArrayList<Entry>()
        val yVals6 = ArrayList<Entry>()

        setYValue(yVals1, picturesList.joyList)
        setYValue(yVals2, picturesList.sorrowList)
        setYValue(yVals3, picturesList.angerList)
        setYValue(yVals4, picturesList.surprisedList)
        setYValue(yVals5, picturesList.exposedList)
        setYValue(yVals6, picturesList.blurredList)


        val set1: LineDataSet
        val set2: LineDataSet
        val set3: LineDataSet
        val set4: LineDataSet
        val set5: LineDataSet
        val set6: LineDataSet

        if (lineChart.data != null && lineChart.getData().getDataSetCount() > 0) {
            set1 = lineChart.getData().getDataSetByIndex(0) as LineDataSet
            set2 = lineChart.getData().getDataSetByIndex(1) as LineDataSet
            set3 = lineChart.getData().getDataSetByIndex(2) as LineDataSet
            set4 = lineChart.getData().getDataSetByIndex(3) as LineDataSet
            set5 = lineChart.getData().getDataSetByIndex(4) as LineDataSet
            set6 = lineChart.getData().getDataSetByIndex(5) as LineDataSet
            set1.values = yVals1
            set2.values = yVals2
            set3.values = yVals3
            set4.values = yVals4
            set5.values = yVals5
            set6.values = yVals6
            lineChart.getData().notifyDataChanged()
            lineChart.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(yVals1, "Joy")

            set1.axisDependency = AxisDependency.RIGHT
            set1.color = Color.parseColor("#3AB795")
            set1.setCircleColor(Color.WHITE)
            set1.lineWidth = 2f
            set1.circleRadius = 3f
            set1.fillAlpha = 65
            set1.fillColor = ColorTemplate.getHoloBlue()
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.setDrawCircleHole(false)

            // create a dataset and give it a type
            set2 = LineDataSet(yVals2, "Sorrow")
            set2.axisDependency = AxisDependency.RIGHT
            set2.color = Color.parseColor("#947EB0")
            set2.setCircleColor(Color.WHITE)
            set2.lineWidth = 2f
            set2.circleRadius = 3f
            set2.fillAlpha = 65
            set2.fillColor = Color.RED
            set2.setDrawCircleHole(false)
            set2.highLightColor = Color.rgb(244, 117, 117)
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set3 = LineDataSet(yVals3, "Anger")
            set3.axisDependency = AxisDependency.RIGHT
            set3.color = Color.parseColor("#F25F5C")
            set3.setCircleColor(Color.WHITE)
            set3.lineWidth = 2f
            set3.circleRadius = 3f
            set3.fillAlpha = 65
            set3.fillColor = ColorTemplate.colorWithAlpha(Color.YELLOW, 200)
            set3.setDrawCircleHole(false)
            set3.highLightColor = Color.rgb(244, 117, 117)

            set4 = LineDataSet(yVals4, "Surprised")
            set4.axisDependency = AxisDependency.RIGHT
            set4.color = Color.parseColor("#ECC8AF")
            set4.setCircleColor(Color.WHITE)
            set4.lineWidth = 2f
            set4.circleRadius = 3f
            set4.fillAlpha = 65
            set4.fillColor = ColorTemplate.colorWithAlpha(Color.MAGENTA, 200)
            set4.setDrawCircleHole(false)
            set4.highLightColor = Color.rgb(244, 117, 117)

            set5 = LineDataSet(yVals5, "Exposed")
            set5.axisDependency = AxisDependency.RIGHT
            set5.color = Color.parseColor("#3C91E6")
            set5.setCircleColor(Color.WHITE)
            set5.lineWidth = 2f
            set5.circleRadius = 3f
            set5.fillAlpha = 65
            set5.fillColor = ColorTemplate.colorWithAlpha(Color.CYAN, 200)
            set5.setDrawCircleHole(false)
            set5.highLightColor = Color.rgb(244, 117, 117)

            set6 = LineDataSet(yVals6, "Blurred")
            set6.axisDependency = AxisDependency.RIGHT
            set6.color = Color.parseColor("#FFE066")
            set6.setCircleColor(Color.WHITE)
            set6.lineWidth = 2f
            set6.circleRadius = 3f
            set6.fillAlpha = 65
            set6.fillColor = ColorTemplate.colorWithAlpha(Color.GREEN, 200)
            set6.setDrawCircleHole(false)
            set6.highLightColor = Color.rgb(244, 117, 117)



            // create a data object with the datasets
            val data = LineData(set1, set2, set3, set4, set5, set6)
            data.setValueTextColor(Color.WHITE)
            data.setValueTextSize(9f)

            // set data
            lineChart.setData(data)
        }
    }
}
