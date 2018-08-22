package scientifik.kplot.jfreechart

import org.jfree.data.xy.AbstractXYDataset
import scientifik.kplot.common.Plot
import scientifik.kplot.common.config.XYPlotConfiguration
import scientifik.kplot.common.config.number
import scientifik.kplot.common.x
import scientifik.kplot.common.y
import java.awt.Color

class JFreeChartPlot(val plot: Plot) : AbstractXYDataset(), Plot by plot {

    val xyMeta: XYPlotConfiguration = XYPlotConfiguration(plot.meta)

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