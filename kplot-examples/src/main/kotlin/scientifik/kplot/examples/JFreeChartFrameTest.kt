package scientifik.kplot.examples

import javafx.application.Application
import javafx.scene.Parent
import scientifik.kplot.appendXY
import scientifik.kplot.config.configure
import scientifik.kplot.config.createStyle
import scientifik.kplot.config.style
import scientifik.kplot.jfreechart.JFreeChartFrame
import scientifik.kplot.specifications.XYPlot
import scientifik.kplot.specifications.XYPlotConfiguration
import scientifik.kplot.xyPlot
import tornadofx.*

class JFreeChartFrameTest : App(TestView::class)

fun main(args: Array<String>) {
    Application.launch(JFreeChartFrameTest::class.java)
}

internal class TestView : View() {
    private val frame = JFreeChartFrame()
    override val root: Parent = frame.root

    init {

        val lineStyle = XYPlot.createStyle {
            connectionType = XYPlotConfiguration.ConnectionType.STEP
            thickness = 4
            showErrors = false
        }

        frame["test"] = xyPlot {
            (1..100).forEach { appendXY(it, it * it) }
        }.style(lineStyle).configure(XYPlot) {
            title = "My test plot"
            //showLines = false
        }
    }

}