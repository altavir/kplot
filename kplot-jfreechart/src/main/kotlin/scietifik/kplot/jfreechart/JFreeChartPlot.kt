package scietifik.kplot.jfreechart

import org.jfree.data.xy.AbstractXYDataset
import scientifik.kplot.common.*
import scientifik.kplot.common.config.XYPlotConfig
import scientifik.kplot.common.config.number
import java.awt.Color

class JFreeChartPlot(val plot: Plot) : AbstractXYDataset(), Plot by plot {

    override val config: XYPlotConfig = XYPlotConfig(plot.config)

    override fun getX(series: Int, item: Int): Number {
        //TODO include multiple series?
        return plot.data.y[item].number!!
    }

    override fun getY(series: Int, item: Int): Number {
        return plot.data.y[item].number!!
    }

    override fun getSeriesKey(series: Int): Comparable<Nothing> = plot.toString()

    override fun getItemCount(series: Int): Int {
        return plot.data.size
    }

    override fun getSeriesCount(): Int = 1

    val awtColor: Color?
        get() = config.color?.let {
            val fxColor = javafx.scene.paint.Color.valueOf(it)
            Color(fxColor.red.toFloat(), fxColor.green.toFloat(), fxColor.blue.toFloat())
        }

    companion object {

        /**
         * Wrap any plot in a JFreeChart plot
         */
        fun wrap(plot: Plot): JFreeChartPlot {
            return plot as? JFreeChartPlot ?: JFreeChartPlot(plot)
        }
    }
}