package team.lf.cryptoapp.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import team.lf.cryptoapp.R
import team.lf.cryptoapp.formatters.dateToString

@SuppressLint("ViewConstructor")
class MyMarker(context: Context, layoutResources: Int):MarkerView(context, layoutResources) {
    private val tvContent: TextView
    init {
        tvContent = findViewById(R.id.tvContent)
    }

    override fun refreshContent(e: Entry, highlight: Highlight?) {
        tvContent.text = e.y.toString() + "\n" + e.x.dateToString("MMM dd, yyyy")
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width/2)).toFloat(), (-height).toFloat())
    }

}