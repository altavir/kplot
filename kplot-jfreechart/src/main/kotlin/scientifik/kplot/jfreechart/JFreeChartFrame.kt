package scientifik.kplot.jfreechart

import hep.dataforge.meta.*
import javafx.application.Platform.runLater
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.DateAxis
import org.jfree.chart.axis.LogarithmicAxis
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.axis.ValueAxis
import org.jfree.chart.fx.ChartViewer
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYErrorRenderer
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.chart.renderer.xy.XYSplineRenderer
import org.jfree.chart.renderer.xy.XYStepRenderer
import org.jfree.chart.title.LegendTitle
import org.jfree.chart.title.TextTitle
import scientifik.kplot.Plot
import scientifik.kplot.PlotFrame
import scientifik.kplot.specifications.GenericAxisSpec
import scientifik.kplot.specifications.GenericFrameSpec
import scientifik.kplot.specifications.XYPlotSpec
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Shape
import java.util.*
import kotlin.math.absoluteValue

class JFreeChartFrame(meta: Config? = null) : PlotFrame {

    private val xyPlot: XYPlot = XYPlot(null, NumberAxis(), NumberAxis(), XYLineAndShapeRenderer())
    private val chart: JFreeChart = JFreeChart(xyPlot)

    // color and shape cache
    private val colorCache = HashMap<String, Color>()
    private val shapeCache = HashMap<String, Shape>()

    /**
     * String to number index for this chart
     */
    private val index = HashMap<String, Int>()

    //TODO store x and y ranges

    override val styledConfig = (meta ?: Config()).withStyle().apply {
        onChange { _, _, _ ->
            //TODO differentiate parameters?
            updateFrame()
        }
    }

    override val layout: GenericFrameSpec = GenericFrameSpec(this.styledConfig.config)

    val root by lazy { ChartViewer(chart) }

    override fun get(key: String): JFreeChartPlot? {
        return index[key]?.let { xyPlot.getDataset(it) } as JFreeChartPlot?
    }

    override fun set(key: String, plot: Plot) {
        synchronized(this) {
            val i = plot.hashCode().absoluteValue
            if (index.containsKey(key)) {
                index.remove(key)
            }
            val wrapper = JFreeChartPlot.wrap(plot)
            xyPlot.setDataset(i, wrapper)
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

    override fun configure(key: String, meta: Meta) {
        get(key)?.let {
            it.config.update(meta)
            render(key)
        }
    }

    private fun buildAxis(meta: GenericAxisSpec): ValueAxis {
        val axis = when (meta.type) {
            GenericAxisSpec.AxisType.LOG -> LogarithmicAxis("").apply {
                //        logAxis.setMinorTickCount(10);
                expTickLabelsFlag = true
                isMinorTickMarksVisible = true
                allowNegativesFlag = false
                autoRangeNextLogFlag = true
                strictValuesFlag = false // Omit negatives but do not throw exception
            }
            GenericAxisSpec.AxisType.TIME -> DateAxis().apply {
                timeZone = TimeZone.getTimeZone(meta["timeZone"]?.string ?: "UTC")
            }
            GenericAxisSpec.AxisType.CATEGORY -> throw IllegalArgumentException("Category asis type not supported by JFreeChartFrame")
            else -> NumberAxis().apply {
                autoRangeIncludesZero = meta["includeZero"]?.boolean ?: false
                autoRangeStickyZero = meta["stickyZero"]?.boolean ?: false
            }
        }

        meta.range.from?.number?.toDouble()?.let {
            if (it.isFinite()) {
                axis.lowerBound = it
            }
        }

        meta.range.to?.number?.toDouble()?.let {
            if (it.isFinite()) {
                axis.upperBound = it
            }
        }

        axis.label = meta.title?.let {
            it + (meta.units?.let { units -> " ($units)" } ?: "")
        } ?: ""

        return axis
    }

    private fun updateXAxis() {
        val axis = buildAxis(layout.getAxis(Plot.X_AXIS))
        /*
                    "x" -> {
                xyPlot.domainAxis = axis
                when (crosshair) {
                    "free" -> {
                        xyPlot.isDomainCrosshairVisible = true
                        xyPlot.isDomainCrosshairLockedOnData = false
                    }
                    "data" -> {
                        xyPlot.isDomainCrosshairVisible = true
                        xyPlot.isDomainCrosshairLockedOnData = true
                    }
                    "none" -> xyPlot.isDomainCrosshairVisible = false
                }
            }
         */
        xyPlot.domainAxis = axis
    }

    private fun updateYAxis() {
        val axis = buildAxis(layout.getAxis(Plot.Y_AXIS))
        /*
                    "x" -> {
                xyPlot.domainAxis = axis
                when (crosshair) {
                    "free" -> {
                        xyPlot.isDomainCrosshairVisible = true
                        xyPlot.isDomainCrosshairLockedOnData = false
                    }
                    "data" -> {
                        xyPlot.isDomainCrosshairVisible = true
                        xyPlot.isDomainCrosshairLockedOnData = true
                    }
                    "none" -> xyPlot.isDomainCrosshairVisible = false
                }
            }
         */
        xyPlot.rangeAxis = axis
    }

    private fun updateLegend() {
        if (layout.legend.visible) {
            if (chart.legend == null) {
                chart.addLegend(LegendTitle(xyPlot))
            }
        } else {
            chart.removeLegend()
        }
        //this.xyPlot.legendItems
    }

    /**
     * Update frame configuration based on layout
     */
    private fun updateFrame() {
        runLater {
            this.chart.title = TextTitle(layout.title ?: "")
            updateLegend()
            updateXAxis()
            updateYAxis()
        }
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
        val render: XYLineAndShapeRenderer = if (xyMeta.showErrors) {
            XYErrorRenderer()
        } else {
            when (xyMeta.connectionType) {
                XYPlotSpec.ConnectionType.STEP -> XYStepRenderer()
                XYPlotSpec.ConnectionType.SPLINE -> XYSplineRenderer()
                else -> XYLineAndShapeRenderer()
            }
        }

        render.defaultShapesVisible = xyMeta.showSymbols
        render.defaultLinesVisible = xyMeta.showLines

        //Build Legend map to avoid serialization issues
        render.setSeriesStroke(0, BasicStroke(xyMeta.thickness.toFloat()))

        (awtColor ?: colorCache[key])?.let { render.setSeriesPaint(0, it) }

        shapeCache[key]?.let { render.setSeriesShape(0, it) }

        render.setSeriesVisible(0, xyMeta.visible)

        render.setLegendItemLabelGenerator { dataset, series ->
            xyMeta.title ?: dataset.getSeriesKey(series).toString()
        }

        return render
    }
}
//
//fun JFreeChartFrame.configure(action: JFreeChartConfig.() -> Unit) {
//    JFreeChartConfig(this.layout).apply(action)
//}