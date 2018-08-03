package scientifik.kplot.common

/**
 * A temporary plug for plot value. Values could be null
 */
typealias Value = Any?

interface PropertyHolder {
    fun getProperty(key: String): Value
    val propertyKeys: List<String>
}