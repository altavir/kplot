package scientifik.kplot.common.config

open class XYPlotConfig(private val config: Config) : Config by config {
    enum class ConnectionType {
        DEFAULT,
        SPLINE,
        STEP
    }

    //TODO move to parent class
    var visible: Boolean by Config.boolean(default = true)
    var showErrors: Boolean by Config.boolean(default = true)
    var showLines: Boolean by Config.boolean(default = true)
    var showSymbols: Boolean by Config.boolean(default = true)
    var title: String? by Config.string()

    var connectionType: ConnectionType
        get() = ConnectionType.valueOf(this["connectionType"].string ?: ConnectionType.DEFAULT.name)
        set(value) {
            this["connectionType"] = value.name
        }

    var thickness: Number by Config.number(default = 2)

    var color: String? by Config.string()
}