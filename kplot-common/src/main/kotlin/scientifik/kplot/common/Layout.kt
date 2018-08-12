package scientifik.kplot.common


/**
 * Axis configuration
 */
interface AxisLayout : Config {
    enum class AxisType{
        AUTO,
        LINEAR,
        LOG,
        TIME,
        CATEGORY
    }

    val type: AxisType

    val title: String
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