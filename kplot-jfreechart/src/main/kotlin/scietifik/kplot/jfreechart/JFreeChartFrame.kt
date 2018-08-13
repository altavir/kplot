package scietifik.kplot.jfreechart

import javafx.application.Platform.runLater
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYErrorRenderer
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.chart.renderer.xy.XYSplineRenderer
import org.jfree.chart.renderer.xy.XYStepRenderer
import scientifik.kplot.common.*
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.util.stream.Collectors
import kotlin.math.absoluteValue

class JFreeChartFrame : PlotFrame {

    private val xyPlot: XYPlot = XYPlot(null, NumberAxis(), NumberAxis(), XYLineAndShapeRenderer())
    private val chart: JFreeChart = JFreeChart(xyPlot)

    private val colorCache = HashMap<String, Color>()
    private val shapeCache = HashMap<String, Shape>()

    /**
     * String to number index for this chart
     */
    private val index = HashMap<String, Int>()

    override var layout: Layout = JFreeChartLayout()
        set(value) {
            field = value
            updateFrame()
            value.onChange { _, _ ->
                updateFrame()
            }
        }

    override fun get(key: String): JFreeChartPlot? {
        return index[key]?.let { xyPlot.getDataset(it) } as JFreeChartPlot?
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

//    override fun append(key: String, data: PlotData) {
//        get(key)?.let{
//            it.append(data)
//            xyPlot.datasetChanged(DatasetChangeEvent(this.xyPlot, it))
//        }
//    }


    /**
     * Update frame configuration based on layout
     */
    private fun updateFrame() {

    }

    /**
     * Update renderer for given key
     */
    private fun render(key: String) {
        index[key]?.let {
            val plot = xyPlot.getDataset(it) as JFreeChartPlot
            val render = plot.createRenderer(key)
            runLater {
                xyPlot.setRenderer(it, render)

                // update cache to default colors
                val paint = render.lookupSeriesPaint(0)
                if (paint is Color) {
                    colorCache[key] = paint
                }
                shapeCache[key] = render.lookupSeriesShape(0)
            }
        }

    }

    private fun JFreeChartPlot.createRenderer(key: String): XYLineAndShapeRenderer {
        val render: XYLineAndShapeRenderer = if (showErrors) {
            XYErrorRenderer()
        } else {
            when (connectionType) {
                JFreeChartPlot.ConnectionType.STEP -> XYStepRenderer()
                JFreeChartPlot.ConnectionType.SPLINE -> XYSplineRenderer()
                else -> XYLineAndShapeRenderer()
            }
        }

        render.defaultShapesVisible = showSymbols
        render.defaultLinesVisible = showLines

        //Build Legend map to avoid serialization issues
        render.setSeriesStroke(0, BasicStroke(thickness.toFloat()))

        (awtColor ?: colorCache[key])?.let { render.setSeriesPaint(0, it) }

        shapeCache[key]?.let { render.setSeriesShape(0, it) }

        render.setSeriesVisible(0, visible)

        render.setLegendItemLabelGenerator { dataset, series -> title ?: dataset.getSeriesKey(series).toString() }

        return render
    }
}