package scientifik.kplot.specifications

import scientifik.kplot.config.*


/**
 * Axis configuration
 */
open class GenericAxisSpecification(meta: Configuration) : Configuration by meta {
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

    var range by spec(Range)
}

object GenericAxis: Specification<GenericAxisSpecification> {
    override fun wrap(config: Configuration): GenericAxisSpecification = GenericAxisSpecification(config)
}

/**
 * Legend configuration. Does not include actual legend items
 */
open class GenericLegendSpecification(meta: Configuration) : Configuration by meta {
    var visible: Boolean by boolean(default = true)
}

object GenericLegend: Specification<GenericLegendSpecification> {
    override fun wrap(config: Configuration): GenericLegendSpecification = GenericLegendSpecification(config)
}

/**
 * Layout configurator
 */
open class GenericFrameSpecification(meta: Configuration) : Configuration by meta {

    var title: String? by string()

    val legend by spec(GenericLegend)

    fun getAxis(axis: String): GenericAxisSpecification {
        return GenericAxisSpecification(child("axis[$axis]"))
    }
}

object GenericFrame: Specification<GenericFrameSpecification> {
    override fun wrap(config: Configuration): GenericFrameSpecification = GenericFrameSpecification(config)
}