package scientifik.kplot.examples

import javafx.application.Application
import javafx.scene.Parent
import scientifik.kplot.common.appendXY
import scientifik.kplot.common.config.*
import scientifik.kplot.common.xyPlot
import scientifik.kplot.jfreechart.JFreeChartFrame
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