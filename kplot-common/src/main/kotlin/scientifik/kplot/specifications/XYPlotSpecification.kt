package scientifik.kplot.specifications

import scientifik.kplot.config.*

open class XYPlotConfiguration(private val meta: Configuration) : Configuration by meta {
    enum class ConnectionType {
        DEFAULT,
        SPLINE,
        STEP
    }

    var visible: Boolean by boolean(default = true)
    var showErrors: Boolean by boolean(default = true)
    var showLines: Boolean by boolean(default = true)
    var showSymbols: Boolean by boolean(default = true)
    var title: String? by string()

    var connectionType by enum(ConnectionType.DEFAULT)

    var thickness: Number by number(default = 2)

    var color: String? by string()
}

object XYPlot: Specification<XYPlotConfiguration> {
    override fun wrap(config: Configuration): XYPlotConfiguration = XYPlotConfiguration(config)
}