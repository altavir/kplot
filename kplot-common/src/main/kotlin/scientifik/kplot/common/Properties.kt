package scientifik.kplot.common

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


typealias PropertyChangeListener = (key: String, value: Value) -> Unit

/**
 * The container for custom properties
 */
interface Config {

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
     * Type safe builder method to add property node
     */
    infix fun String.to(action: Config.() -> Unit)

    companion object {
        /**
         * A generic property delegate that uses its name to resolve the value
         */
        val property = PropertyHolderDelegate()

        /**
         * A property delegate that uses custom key
         */
        fun property(key: String) = PropertyHolderDelegate(key)
    }
}

fun Config.update(other: Config) {
    other.keys.forEach { key ->
        this[key] = other[key]
    }
}

/**
 * Basic implementation for a property holder
 */
class PropertyMap(private val map: MutableMap<String, Value> = HashMap()) : Config {
    private val listeners: MutableSet<PropertyChangeListener> = HashSet()

    override val keys: Collection<String> = map.keys

    /**
     * Apply "soft" path-name search meaning that element with name containing dots is returned as is, but if it is not found,
     * the search is delegated to the entry found by the first token separated by `.`. The use of `.` in names should be avoided
     */
    override fun get(key: String): Value = map[key] ?: run {
        (map[key.substringBefore(".")] as? PropertyMap)?.get(key.substringAfter("."))
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

    override infix fun String.to(action: Config.() -> Unit) {
        val holder = PropertyMap().apply(action)
        holder.onChange { key, value ->
            invalidate("${this}.$key")
        }
        set(this, holder)
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

class PropertyHolderDelegate(private val key: String? = null) : ReadWriteProperty<Config, Value> {

    override fun getValue(thisRef: Config, property: KProperty<*>): Value {
        return thisRef[key ?: property.name]
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Value) {
        thisRef[key ?: property.name] = value
    }

}