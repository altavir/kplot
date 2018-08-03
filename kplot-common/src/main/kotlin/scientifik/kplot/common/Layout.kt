package scientifik.kplot.common


/**
 * Axis configuration
 */
interface AxisLayout : PropertyHolder {

}

interface LegendLayout : PropertyHolder {

}

/**
 * Layout configurator
 */
interface Layout : PropertyHolder {
    fun getAxis(axis: String): AxisLayout
    val legend: LegendLayout
}