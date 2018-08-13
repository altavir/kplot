package scientifik.kplot.common

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class ConfigDelegate(private val key: String? = null, private val default: Value = null) : ReadWriteProperty<Config, Value> {
    override fun getValue(thisRef: Config, property: KProperty<*>): Value {
        return thisRef[key ?: property.name] ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Value) {
        thisRef[key ?: property.name] = value
    }
}

class StringConfigDelegate(private val key: String? = null, private val default: String? = null) : ReadWriteProperty<Config, String?> {
    override fun getValue(thisRef: Config, property: KProperty<*>): String? {
        return thisRef[key ?: property.name].string ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: String?) {
        thisRef[key ?: property.name] = value
    }
}

class BooleanConfigDelegate(private val key: String? = null, private val default: Boolean? = null) : ReadWriteProperty<Config, Boolean?> {
    override fun getValue(thisRef: Config, property: KProperty<*>): Boolean? {
        return thisRef[key ?: property.name].boolean ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Boolean?) {
        thisRef[key ?: property.name] = value
    }
}

class NumberConfigDelegate(private val key: String? = null, private val default: Number? = null) : ReadWriteProperty<Config, Number?> {
    override fun getValue(thisRef: Config, property: KProperty<*>): Number? {
        return thisRef[key ?: property.name].number ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Number?) {
        thisRef[key ?: property.name] = value
    }
}

//Delegates with non-null values

class SafeStringConfigDelegate(private val key: String? = null, private val default: String) : ReadWriteProperty<Config, String> {
    override fun getValue(thisRef: Config, property: KProperty<*>): String {
        return thisRef[key ?: property.name].string ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: String) {
        thisRef[key ?: property.name] = value
    }
}

class SafeBooleanConfigDelegate(private val key: String? = null, private val default: Boolean) : ReadWriteProperty<Config, Boolean> {
    override fun getValue(thisRef: Config, property: KProperty<*>): Boolean {
        return thisRef[key ?: property.name].boolean ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Boolean) {
        thisRef[key ?: property.name] = value
    }
}

class SafeNumberConfigDelegate(private val key: String? = null, private val default: Number) : ReadWriteProperty<Config, Number> {
    override fun getValue(thisRef: Config, property: KProperty<*>): Number {
        return thisRef[key ?: property.name].number ?: default
    }

    override fun setValue(thisRef: Config, property: KProperty<*>, value: Number) {
        thisRef[key ?: property.name] = value
    }
}