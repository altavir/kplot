package scientifik.kplot.common


/**
 * Axis configuration
 */
interface AxisLayout : Config {
    enum class AxisType {
        AUTO,
        LINEAR,
        LOG,
        TIME,
        CATEGORY
    }

    var type: AxisType

    var title: String
}

/**
 * Legend configuration. Does not include actual legend items
 */
interface LegendLayout : Config {

}

/**
 * Layout configurator
 */
interface Layout : Config {
    fun getAxis(axis: String): AxisLayout
    val legend: LegendLayout
}

class ConfigLegend(config: Config) : LegendLayout, Config by config {

}

class ConfigAxis(config: Config): AxisLayout, Config by config{
    override var type: AxisLayout.AxisType
        get() = AxisLayout.AxisType.valueOf(this["type"].string)
        set(value) {
            this["type"] = value.name
        }

    override var title: String by Config.string()
}

class ConfigLayout(config: Config) : Layout, Config by config {
    override fun getAxis(axis: String): AxisLayout {
        return ConfigAxis(getChild("axis[$axis]"))
    }

    override val legend: LegendLayout = ConfigLegend(getChild("legend"))

}