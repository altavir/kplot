package scientifik.kplot.common.config


/**
 * Axis configuration
 */
open class AxisConfiguration(meta: Configuration) : Configuration by meta {
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

    var title: String? by Configuration.string()

    var units: String? by Configuration.string()

    var autoRange: Boolean by Configuration.boolean(default = true)

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
open class LegendConfiguration(meta: Configuration) : Configuration by meta {
    var visible: Boolean by Configuration.boolean(default = true)
}

/**
 * Layout configurator
 */
open class FrameConfiguration(meta: Configuration) : Configuration by meta {

    var title: String? by Configuration.string()

    val legend: LegendConfiguration = LegendConfiguration(getChild("legend"))

    fun getAxis(axis: String): AxisConfiguration {
        return AxisConfiguration(getChild("axis[$axis]"))
    }
}