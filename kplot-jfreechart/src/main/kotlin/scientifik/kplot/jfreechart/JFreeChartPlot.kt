package scientifik.kplot.jfreechart

import org.jfree.data.xy.AbstractXYDataset
import scientifik.kplot.Plot
import scientifik.kplot.specifications.XYPlot
import scientifik.kplot.specifications.XYPlotSpec
import scientifik.kplot.x
import scientifik.kplot.y
import java.awt.Color

class JFreeChartPlot(val plot: Plot) : AbstractXYDataset(), Plot by plot {

    val xyMeta: XYPlotSpec = XYPlot.wrap(plot.styledConfig)

    override fun getX(series: Int, item: Int): Number? {
        return plot.data.x[item].number
    }

    override fun getY(series: Int, item: Int): Number? {
        return plot.data.y[item].number
    }

    override fun getSeriesKey(series: Int): Comparable<Nothing> = plot.toString()

    override fun getItemCount(series: Int): Int {
        return plot.data.size
    }

    override fun getSeriesCount(): Int = 1

    val awtColor: Color?
        get() = xyMeta.color?.let {
            val fxColor = javafx.scene.paint.Color.valueOf(it)
            Color(fxColor.red.toFloat(), fxColor.green.toFloat(), fxColor.blue.toFloat())
        }

    companion object {

        /**
         * Wrap any plot in a JFreeChart plot
         */
        fun wrap(plot: Plot): JFreeChartPlot {
            return plot as? JFreeChartPlot
                    ?: JFreeChartPlot(plot)
        }
    }
}