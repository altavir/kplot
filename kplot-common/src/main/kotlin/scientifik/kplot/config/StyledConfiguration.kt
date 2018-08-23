package scientifik.kplot.config

/**
 * A configuration with changeable style
 */
open class StyledConfiguration(private val configuration: Configuration, style: Meta) : Configuration by configuration {

    var style = style
        set(value) {
            field = value
            invalidate()
        }

    override val keys: Collection<String>
        get() = (configuration.keys + style.keys).distinct()

    override fun get(key: String): Value {
        return configuration[key] ?: style[key]
    }
}

fun <T : Configuration> T.asStyleable() = if (this is StyledConfiguration) {
    this
} else {
    StyledConfiguration(this, empty())
}

fun <T : Configuration> T.applyStyle(style: Meta) = if (this is StyledConfiguration) {
    this.style = style
    this
} else {
    StyledConfiguration(this, style)
}