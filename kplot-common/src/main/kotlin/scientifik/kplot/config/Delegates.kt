package scientifik.kplot.config

import kotlin.jvm.JvmName
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ConfigDelegate(private val key: String? = null, private val default: Value = null) : ReadWriteProperty<Configuration, Value> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): Value {
        return thisRef[key ?: property.name] ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: Value) {
        thisRef[key ?: property.name] = value
    }
}

class StringConfigDelegate(private val key: String? = null, private val default: String? = null) : ReadWriteProperty<Configuration, String?> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): String? {
        return thisRef[key ?: property.name].string ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: String?) {
        thisRef[key ?: property.name] = value
    }
}

class BooleanConfigDelegate(private val key: String? = null, private val default: Boolean? = null) : ReadWriteProperty<Configuration, Boolean?> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): Boolean? {
        return thisRef[key ?: property.name].boolean ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: Boolean?) {
        thisRef[key ?: property.name] = value
    }
}

class NumberConfigDelegate(private val key: String? = null, private val default: Number? = null) : ReadWriteProperty<Configuration, Number?> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): Number? {
        return thisRef[key ?: property.name].number ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: Number?) {
        thisRef[key ?: property.name] = value
    }
}

//Delegates with non-null values

class SafeStringConfigDelegate(private val key: String? = null, private val default: String) : ReadWriteProperty<Configuration, String> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): String {
        return thisRef[key ?: property.name].string ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: String) {
        thisRef[key ?: property.name] = value
    }
}

class SafeBooleanConfigDelegate(private val key: String? = null, private val default: Boolean) : ReadWriteProperty<Configuration, Boolean> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): Boolean {
        return thisRef[key ?: property.name].boolean ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: Boolean) {
        thisRef[key ?: property.name] = value
    }
}

class SafeNumberConfigDelegate(private val key: String? = null, private val default: Number) : ReadWriteProperty<Configuration, Number> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): Number {
        return thisRef[key ?: property.name].number ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: Number) {
        thisRef[key ?: property.name] = value
    }
}

class SafeEnumvConfigDelegate<E : Enum<E>>(private val key: String? = null, private val default: E, private val resolver: (String) -> E) : ReadWriteProperty<Configuration, E> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): E {
        return (thisRef[key ?: property.name].string)?.let { resolver(it) } ?: default
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: E) {
        thisRef[key ?: property.name] = value.name
    }
}

//Child node delegate

class ChildConfigDelegate<T : Configuration>(private val key: String? = null, private val converter: (Configuration) -> T) : ReadWriteProperty<Configuration, T> {
    override fun getValue(thisRef: Configuration, property: KProperty<*>): T {
        return converter(thisRef.child(key ?: property.name))
    }

    override fun setValue(thisRef: Configuration, property: KProperty<*>, value: T) {
        thisRef[key ?: property.name] = value
    }

}

/**
 * A property delegate that uses custom key
 */
fun Configuration.value(key: String? = null, default: Value = null) = ConfigDelegate(key, default)

fun Configuration.string(key: String? = null, default: String? = null) = StringConfigDelegate(key, default)

fun Configuration.boolean(key: String? = null, default: Boolean? = null) = BooleanConfigDelegate(key, default)

fun Configuration.number(key: String? = null, default: Number? = null) = NumberConfigDelegate(key, default)

fun Configuration.child(key: String? = null) = ChildConfigDelegate<Configuration>(key){it}

fun <T : Configuration> Configuration.child(key: String? = null, converter: (Configuration) -> T) = ChildConfigDelegate<T>(key, converter)

fun <T : Configuration> Configuration.spec(spec: Specification<T>, key: String? = null) = ChildConfigDelegate<T>(key) { spec.wrap(this) }

@JvmName("safeString")
fun Configuration.string(key: String? = null, default: String) = SafeStringConfigDelegate(key, default)

@JvmName("safeBoolean")
fun Configuration.boolean(key: String? = null, default: Boolean) = SafeBooleanConfigDelegate(key, default)

@JvmName("safeNumber")
fun Configuration.number(key: String? = null, default: Number) = SafeNumberConfigDelegate(key, default)

inline fun <reified E : Enum<E>> Configuration.enum(default: E, key: String? = null) = SafeEnumvConfigDelegate(key, default) { enumValueOf(it) }