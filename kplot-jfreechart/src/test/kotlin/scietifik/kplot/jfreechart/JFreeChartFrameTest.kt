package scietifik.kplot.jfreechart

import javafx.application.Application
import javafx.scene.Parent
import org.junit.jupiter.api.Assertions.*
import scientifik.kplot.common.appendXY
import scientifik.kplot.common.xyPlot
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
        }
    }

}