package scientifik.kplot.specifications

import scientifik.kplot.config.*


/**
 * Axis configuration
 */
open class GenericAxisConfig(meta: Configuration) : Configuration by meta {
    enum class AxisType {
        AUTO,
        LINEAR,
        LOG,
        TIME,
        CATEGORY
    }

    var type by enum(AxisType.AUTO)

    var title: String? by string()

    var units: String? by string()

    var autoRange: Boolean by boolean(default = true)

    var range by spec(RangeSpec)
}

object GenericAxis: Specification<GenericAxisConfig> {
    override fun wrap(config: Configuration): GenericAxisConfig = GenericAxisConfig(config)
}

/**
 * Legend configuration. Does not include actual legend items
 */
open class GenericLegendConfig(meta: Configuration) : Configuration by meta {
    var visible: Boolean by boolean(default = true)
}

object GenericLegend: Specification<GenericLegendConfig> {
    override fun wrap(config: Configuration): GenericLegendConfig = GenericLegendConfig(config)
}

/**
 * Layout configurator
 */
open class GenericFrameConfig(meta: Configuration) : Configuration by meta {

    var title: String? by string()

    val legend by spec(GenericLegend)

    fun getAxis(axis: String): GenericAxisConfig {
        return GenericAxisConfig(child("axis[$axis]"))
    }
}

object GenericFrame: Specification<GenericFrameConfig> {
    override fun wrap(config: Configuration): GenericFrameConfig = GenericFrameConfig(config)
}