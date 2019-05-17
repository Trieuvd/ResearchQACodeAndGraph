package com.example.researchqacodeandgraph

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.graph_activity.*

class GraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.graph_activity)
        customViewGraph()
    }

    private fun customViewGraph() {
        lcRevenue.apply {
            // background color
            setBackgroundColor(Color.WHITE)

            // disable description text
            description.isEnabled = false

            // enable touch gestures
            setTouchEnabled(true)

            //disable show grid
            setDrawGridBackground(false)
            xAxis.isEnabled = false

            // enable scaling and dragging
            isDragEnabled = true
            setScaleEnabled(true)

            //hide dual axis
            axisLeft.isEnabled = false
            axisRight.isEnabled = false

            //hide legend
            legend.isEnabled = false
        }

        setDataGraph(5, 10f)
    }

    /**
     * xAxis is count
     * yAxis is range
     */
    private fun setDataGraph(count: Int, range: Float) {
        val values = mutableListOf<Entry>()

        for (i in 0..count) {
            val value = (Math.random() * range).toFloat()
            values.add(Entry(i.toFloat(), value))
        }

        // create a dataset and give it a type
        val lineDataSet = LineDataSet(values, "Dataset demo").apply {
            setDrawIcons(false)
            // black lines and points
            color = Color.BLACK
            setCircleColor(Color.BLACK)

            //line thickness and point size
            lineWidth = 1.5f
            circleRadius = 6f

            // set circle hole for point
            setDrawCircleHole(true)
            circleHoleRadius = 5f
        }

        // create a data object with the data sets
        val data = LineData(lineDataSet)

        // set data
        lcRevenue.data = data
    }
}