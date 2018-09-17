package scientifik.kplot.specifications

import hep.dataforge.meta.*

open class XYPlotSpec(override val config: Config) : Specification {
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

object XYPlot: SpecificationBuilder<XYPlotSpec> {
    override fun wrap(config: Config): XYPlotSpec = XYPlotSpec(config)
}