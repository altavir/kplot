package scientifik.kplot.common.config

import scientifik.kplot.common.*
import kotlin.jvm.JvmName


typealias PropertyChangeListener = (key: String, value: Value) -> Unit

/**
 * The container for custom properties. Lightweight variant of DataForge Meta
 *
 * TODO separate DataForge Meta definition in a separate artifact and use it here
 */
interface Configuration {

    /**
     * Get property for given key
     */
    operator fun get(key: String): Value

    /**
     * Set property for given key
     */
    operator fun set(key: String, value: Value)

    /**
     * List top level keys for all registered properties
     */
    val keys: Collection<String>

    /**
     * Add listener to change of state of this property holder. If this property holder has children, they are also triggering this listener
     */
    fun onChange(listener: PropertyChangeListener)

    fun removeListener(listener: PropertyChangeListener)

    /**
     * Externally trigger change event on property with given key
     */
    fun invalidate(key: String)

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
    infix fun String.to(action: Configuration.() -> Unit){
        this to ConfigurationMap().apply(action)
    }

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

fun Configuration.update(other: Configuration) {
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

    override fun invalidate(key: String) = parent.invalidate("$path.$key")

    override fun String.to(meta: Configuration) = with(parent) { "$path.${this@to}" to meta }
}

fun Configuration.getChild(path: String) = ChildConfiguration(this, path)

/**
 * Basic implementation for a property holder
 */
class ConfigurationMap(private val map: MutableMap<String, Value> = HashMap()) : Configuration {
    private val listeners: MutableSet<PropertyChangeListener> = HashSet()

    override val keys: Collection<String> = map.keys

    /**
     * Apply "soft" path-name search meaning that element with name containing dots is returned as is, but if it is not found,
     * the search is delegated to the entry found by the first token separated by `.`. The use of `.` in names should be avoided
     */
    override fun get(key: String): Value = map[key] ?: run {
        (map[key.substringBefore(".")] as? ConfigurationMap)?.get(key.substringAfter("."))
    }

    /**
     * Use "hard" path-name, meaning than intermediate nodes are created for each segment between `.`
     */
    override fun set(key: String, value: Value) {
        if (key.contains(".")) {
            key.substringBefore(".") to {
                this[key.substringAfter(".")] = value
            }
        } else {
            map[key] = value
        }
        invalidate(key)
    }

    override fun String.to(meta: Configuration) {
        meta.onChange { key, _ ->
            invalidate("${this}.$key")
        }
        set(this, meta)
    }

    override fun onChange(listener: PropertyChangeListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: PropertyChangeListener) {
        listeners.remove(listener)
    }

    override fun invalidate(key: String) {
        listeners.forEach { it.invoke(key, get(key)) }
    }
}