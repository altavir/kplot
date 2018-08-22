package scientifik.kplot.common.config

import scientifik.kplot.common.ConfigurationMap

/**
 * Specification allows to apply custom configuration in a type safe way to simple untyped configuration
 */
interface Specification<T> {
    fun update(meta: Configuration, action: T.() -> Unit)
}

/**
 * [Configuration] holder
 */
interface Configurable {
    val meta: Configuration
}

/**
 * [StyledConfiguration] holder
 */
interface Styleable : Configurable {
    override val meta: StyledConfiguration
}

/**
 * Apply style to configurable
 */
fun <T : Configurable> T.configure(meta: Meta): T = apply { this.meta.update(meta) }

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

/**
 * Create a style based on given specification
 */
fun <C : Configuration, S : Specification<C>> S.createStyle(action: C.() -> Unit): Meta = ConfigurationMap().also { update(it, action) }

/**
 * Delegate style altering to [StyledConfiguration] inside
 */
var Styleable.style: Meta
    get() = this.meta.style
    set(value) {
        this.meta.style = value
    }

fun <T:Styleable> T.style(style: Meta) = apply { this.style = style }