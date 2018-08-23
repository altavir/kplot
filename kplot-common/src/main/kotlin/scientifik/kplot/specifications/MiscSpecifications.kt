package scientifik.kplot.specifications

import scientifik.kplot.config.Configuration
import scientifik.kplot.config.Specification
import scientifik.kplot.config.value

class RangeSpecification(meta: Configuration) : Configuration by meta {
    val from by value()
    val to by value()
}

object Range: Specification<RangeSpecification> {
    override fun wrap(config: Configuration): RangeSpecification = RangeSpecification(config)
}