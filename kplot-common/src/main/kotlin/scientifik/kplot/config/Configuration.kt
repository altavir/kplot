package scientifik.kplot.config


/**
 * If key is null, invalidate all keys
 */
typealias PropertyChangeListener = (key: String?, value: Value) -> Unit

/**
 * The container for custom properties. Lightweight variant of DataForge Meta
 *
 * TODO separate DataForge Meta definition in an artifact and use it here
 */
interface Configuration : Meta {


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
     * Create an empty configuration of the same type
     */
    fun empty(): Configuration
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

/**
 * Create a child configuration that delegates all operations to parent. Child could be attached to non-existing node
 */
fun Configuration.child(path: String) = ChildConfiguration(this, path)