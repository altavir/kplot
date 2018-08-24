package scientifik.kplot.specifications

import scientifik.kplot.config.Configuration
import scientifik.kplot.config.Specification
import scientifik.kplot.config.value

class RangeConfig(meta: Configuration) : Configuration by meta {
    val from by value()
    val to by value()
}

object RangeSpec: Specification<RangeConfig> {
    override fun wrap(config: Configuration): RangeConfig = RangeConfig(config)
}