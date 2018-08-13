package scietifik.kplot.jfreechart

import scientifik.kplot.common.*

class JFreeChartLayout(config: Config = ConfigMap()): Layout, Config by config {
    override fun getAxis(axis: String): AxisLayout {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val legend: LegendLayout
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}