package scietifik.kplot.jfreechart

import org.jfree.data.xy.AbstractXYDataset
import scientifik.kplot.common.*
import java.awt.Color

class JFreeChartPlot(private val plot: Plot) : AbstractXYDataset(), Plot by plot {
    override fun getX(series: Int, item: Int): Number {
        //TODO include multiple series?
        return data.y[item].number!!
    }

    override fun getY(series: Int, item: Int): Number {
        return data.y[item].number!!
    }

    override fun getSeriesKey(series: Int): Comparable<Nothing> = plot.toString()

    override fun getItemCount(series: Int): Int {
        return data.size
    }

    override fun getSeriesCount(): Int = 1

    enum class ConnectionType {
        DEFAULT,
        SPLINE,
        STEP
    }

    //TODO move to parent class
    var visible: Boolean by Config.boolean(default = true)
    var showErrors: Boolean by Config.boolean(default = true)
    var showLines: Boolean by Config.boolean(default = true)
    var showSymbols: Boolean by Config.boolean(default = true)
    var title: String? by Config.string()

    var connectionType: ConnectionType
        get() = ConnectionType.valueOf(this["connectionType"].string ?: ConnectionType.DEFAULT.name)
        set(value) {
            this["connectionType"] = value.name
        }

    var thickness: Number by Config.number(default = 2)

    var color: String? by Config.string()

    val awtColor: Color?
        get() = color?.let {
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