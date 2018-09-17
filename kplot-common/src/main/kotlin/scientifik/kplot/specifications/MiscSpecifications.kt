package scientifik.kplot.specifications

import hep.dataforge.meta.Config
import hep.dataforge.meta.Specification
import hep.dataforge.meta.SpecificationBuilder
import hep.dataforge.meta.value

class RangeConfig(override  val config: Config) : Specification {
    val from by value()
    val to by value()
}

object RangeSpec: SpecificationBuilder<RangeConfig> {
    override fun wrap(config: Config): RangeConfig = RangeConfig(config)
}