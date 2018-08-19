package scientifik.kplot.common.config

/**
 * Specification allows to apply custom configuration in a type safe way to simple untyped configuration
 */
interface Specification<T> {
    fun update(meta: Configuration, action: T.() -> Unit)
}

interface Configurable {
    val meta: Configuration
}

/**
 * Apply non-specific configuration to the configurable object
 */
fun <T : Configurable> T.configure(action: Configuration.() -> Unit): T = apply { meta.apply(action) }

/**
 * Apply specified configuration to configurable
 */
fun <T : Configurable, C : Configuration, S : Specification<C>> T.configure(spec: S, action: C.() -> Unit) = apply { spec.update(meta, action) }

/**
 * Update configuration using given specification
 */
fun <C : Configuration, S : Specification<C>> Configuration.update(spec: S, action: C.() -> Unit) = apply { spec.update(this, action) }