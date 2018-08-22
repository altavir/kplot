package scientifik.kplot.common.config

import scientifik.kplot.common.*
import kotlin.jvm.JvmName


/**
 * If key is null, invalidate all keys
 */
typealias PropertyChangeListener = (key: String?, value: Value) -> Unit

interface Meta{

    /**
     * Get property for given key
     */
    operator fun get(key: String): Value


    /**
     * List top level keys for all registered properties
     */
    val keys: Collection<String>

}

/**
 * The container for custom properties. Lightweight variant of DataForge Meta
 *
 * TODO separate DataForge Meta definition in an artifact and use it here
 */
interface Configuration: Meta {


    /**
     * Set property for given key
     */
    operator fun set(key: String, value: Value)

    /**
     * Add listener to change of state of this property holder. If this property holder has children, they are also triggering this listener
     */
    fun onChange(listener: PropertyChangeListener)

    fun removeListener(listener: PropertyChangeListener)

    /**
     * Externally trigger change event on property with given key. If called with null argument, invalidates the whole configuration
     */
    fun invalidate(key: String? = null)

    /**
     * Type safe builder method to add value
     */
    infix fun String.to(value: Value) {
        set(this, value)
    }

    /**
     * Add the whole node to configuration
     */
    infix fun String.to(meta: Configuration)

    /**
     * Type safe builder method to add property node
     */
    infix fun String.to(action: Configuration.() -> Unit) {
        this to empty().apply(action)
    }

    /**
     * Create an empty configuration
     */
    fun empty(): Configuration

    companion object {

        /**
         * A property delegate that uses custom key
         */
        fun value(key: String? = null, default: Value = null) = ConfigDelegate(key, default)

        fun string(key: String? = null, default: String? = null) = StringConfigDelegate(key, default)

        fun boolean(key: String? = null, default: Boolean? = null) = BooleanConfigDelegate(key, default)

        fun number(key: String? = null, default: Number? = null) = NumberConfigDelegate(key, default)

        @JvmName("safeString")
        fun string(key: String? = null, default: String) = SafeStringConfigDelegate(key, default)

        @JvmName("safeBoolean")
        fun boolean(key: String? = null, default: Boolean) = SafeBooleanConfigDelegate(key, default)

        @JvmName("safeNumber")
        fun number(key: String? = null, default: Number) = SafeNumberConfigDelegate(key, default)
    }
}

fun Configuration.update(other: Meta) {
    other.keys.forEach { key ->
        this[key] = other[key]
    }
}

/**
 * A decorator config node that delegates everything to parent
 */
class ChildConfiguration(private val parent: Configuration, private val path: String) : Configuration {
    override fun get(key: String): Value = parent["$path.$key"]

    override fun set(key: String, value: Value) {
        parent["$path.$key"] = value
    }

    override val keys: Collection<String> = parent.keys.filter { it.startsWith(path) }


    override fun onChange(listener: PropertyChangeListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeListener(listener: PropertyChangeListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun invalidate(key: String?) = parent.invalidate(key?.let { "$path.$it" })

    override fun String.to(meta: Configuration) = with(parent) { "$path.${this@to}" to meta }

    override fun empty(): Configuration = parent.empty()
}

fun Configuration.getChild(path: String) = ChildConfiguration(this, path)