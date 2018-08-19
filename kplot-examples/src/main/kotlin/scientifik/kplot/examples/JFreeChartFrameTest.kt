package scientifik.kplot.examples

import javafx.application.Application
import javafx.scene.Parent
import scientifik.kplot.common.appendXY
import scientifik.kplot.common.config.XYPlot
import scientifik.kplot.common.config.configure
import scientifik.kplot.common.xyPlot
import scietifik.kplot.jfreechart.JFreeChartFrame
import tornadofx.*

class JFreeChartFrameTest : App(TestView::class)

fun main(args: Array<String>) {
    Application.launch(JFreeChartFrameTest::class.java)
}

internal class TestView : View() {
    val frame = JFreeChartFrame()
    override val root: Parent = frame.root

    init {
        frame["test"] = xyPlot {
            (1..100).forEach {
                appendXY(it, it * it)
            }
        }.configure(XYPlot) {
            title = "My test plot"
        }
    }

}