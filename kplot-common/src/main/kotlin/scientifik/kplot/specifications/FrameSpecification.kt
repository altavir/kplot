package scientifik.kplot.specifications

import hep.dataforge.meta.*


/**
 * Axis configuration
 */
open class GenericAxisSpec(override val config: Config) : Specification {

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

object GenericAxis: SpecificationBuilder<GenericAxisSpec> {
    override fun wrap(config: Config): GenericAxisSpec = GenericAxisSpec(config)
}

/**
 * Legend configuration. Does not include actual legend items
 */
open class GenericLegendSpec(override val config: Config) : Specification {
    var visible: Boolean by boolean(default = true)
}

object GenericLegend: SpecificationBuilder<GenericLegendSpec> {
    override fun wrap(config: Config): GenericLegendSpec = GenericLegendSpec(config)
}

/**
 * Layout configurator
 */
open class GenericFrameSpec(override  val config: Config) : Specification {

    var title: String? by string()

    val legend by spec(GenericLegend)

    fun getAxis(axis: String): GenericAxisSpec {
        return GenericAxisSpec(this.config["axis[$axis]"]!!.node)
    }
}

object GenericFrame: SpecificationBuilder<GenericFrameSpec> {
    override fun wrap(config: Config): GenericFrameSpec = GenericFrameSpec(config)
}