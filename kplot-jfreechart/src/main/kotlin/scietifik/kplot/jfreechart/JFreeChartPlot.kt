package scietifik.kplot.jfreechart

import org.jfree.data.xy.AbstractXYDataset
import scientifik.kplot.common.*

class JFreeChartPlot(private val plot: Plot) : AbstractXYDataset(), Plot by plot {

    override fun getX(series: Int, item: Int): Number {
        //TODO include multiple series?
        return data.y[item].number
    }

    override fun getY(series: Int, item: Int): Number {
        return data.y[item].number
    }

    override fun getSeriesKey(series: Int): Comparable<Nothing> = plot.toString()

    override fun getItemCount(series: Int): Int {
        return data.size
    }

    override fun getSeriesCount(): Int = 1

    fun append(data: PlotData) {
        TODO("implement")
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