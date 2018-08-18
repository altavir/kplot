package scientifik.kplot.common.config


/**
 * Axis configuration
 */
open class AxisConfig(config: Config) : Config by config {
    enum class AxisType {
        AUTO,
        LINEAR,
        LOG,
        TIME,
        CATEGORY
    }

    var type: AxisType
        get() = AxisType.valueOf(this["type"].string ?: AxisType.AUTO.name)
        set(value) {
            this["type"] = value.name
        }

    var title: String? by Config.string()

    var units: String? by Config.string()

    var autoRange: Boolean by Config.boolean(default = true)

    var range
        get() = Range(get("range.from"), get("range.to"))
        set(value) {
            set("range.from", value.from)
            set("range.to", value.to)
        }
}

/**
 * Legend configuration. Does not include actual legend items
 */
open class LegendConfig(config: Config) : Config by config {
    var visible: Boolean by Config.boolean(default = true)
}

/**
 * Layout configurator
 */
open class FrameConfig(config: Config) : Config by config {

    var title: String? by Config.string()

    val legend: LegendConfig = LegendConfig(getChild("legend"))

    fun getAxis(axis: String): AxisConfig {
        return AxisConfig(getChild("axis[$axis]"))
    }
}