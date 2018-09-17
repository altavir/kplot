package scientifik.kplot.examples

import hep.dataforge.meta.configure
import hep.dataforge.meta.createStyle
import javafx.application.Application
import scientifik.kplot.appendXY
import scientifik.kplot.jfreechart.JFreeChartFrame
import scientifik.kplot.specifications.XYPlot
import scientifik.kplot.specifications.XYPlotSpec
import scientifik.kplot.xyPlot
import tornadofx.*

class JFreeChartFrameTest : App(TestView::class)

fun main(args: Array<String>) {
    Application.launch(JFreeChartFrameTest::class.java)
}

internal class TestView : View() {
    private val frame = JFreeChartFrame()
    override val root = borderpane {
        center = frame.root
    }

    init {

        val lineStyle = XYPlot.createStyle {
            connectionType = XYPlotSpec.ConnectionType.STEP
            thickness = 4
            showErrors = false
        }

        frame["test"] = xyPlot(style = lineStyle) {
            (1..100).forEach { appendXY(it, it * it) }
        }.configure(XYPlot) {
            title = "My test plot"
            //showLines = false
        }
    }

}