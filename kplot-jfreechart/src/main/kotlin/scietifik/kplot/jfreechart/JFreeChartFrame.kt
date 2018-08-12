package scietifik.kplot.jfreechart

import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import scientifik.kplot.common.*
import java.util.*
import kotlin.math.absoluteValue

class JFreeChartFrame : PlotFrame {

    private val xyPlot: XYPlot = XYPlot(null, NumberAxis(), NumberAxis(), XYLineAndShapeRenderer())
    private val chart: JFreeChart = JFreeChart(xyPlot)

    /**
     * String to number index for this chart
     */
    private val index = HashMap<String, Int>()

    override val layout: Layout
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun get(key: String): JFreeChartPlot? {
        return index[key]?.let { xyPlot.getDataset(it) } as JFreeChartPlot?
    }

    /**
     * Update renderer for given key
     */
    private fun render(key: String) {

    }

    override fun set(key: String, plot: Plot) {
        synchronized(this) {
            val i = plot.hashCode().absoluteValue
            if (index.containsKey(key)) {
                index.remove(key)
            }
            xyPlot.setDataset(i, JFreeChartPlot.wrap(plot))
            index[key] = i
            render(key)
        }
    }

    override fun remove(key: String) {
        synchronized(this) {
            index[key]?.let { xyPlot.setDataset(it, null) }
            index.remove(key)
        }
    }

    override fun configure(key: String, config: Config) {
        get(key)?.let {
            it.configure(config)
            render(key)
        }
    }

    override fun append(key: String, data: PlotData) {
        get(key)?.append(data)
    }
}