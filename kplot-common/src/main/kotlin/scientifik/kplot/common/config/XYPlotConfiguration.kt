package scientifik.kplot.common.config

open class XYPlotConfiguration(private val meta: Configuration) : Configuration by meta {
    enum class ConnectionType {
        DEFAULT,
        SPLINE,
        STEP
    }

    //TODO move to parent class
    var visible: Boolean by Configuration.boolean(default = true)
    var showErrors: Boolean by Configuration.boolean(default = true)
    var showLines: Boolean by Configuration.boolean(default = true)
    var showSymbols: Boolean by Configuration.boolean(default = true)
    var title: String? by Configuration.string()

    var connectionType: ConnectionType
        get() = ConnectionType.valueOf(this["connectionType"].string ?: ConnectionType.DEFAULT.name)
        set(value) {
            this["connectionType"] = value.name
        }

    var thickness: Number by Configuration.number(default = 2)

    var color: String? by Configuration.string()
}

object XYPlot: Specification<XYPlotConfiguration>{
    override fun update(meta: Configuration, action: XYPlotConfiguration.() -> Unit) {
        XYPlotConfiguration(meta).apply(action)
    }
}