package scientifik.kplot.config

/**
 * A temporary plug for plot value. Values could be null
 */
typealias Value = Any?

val Value.number: Number?
    get() = (this as? Number) ?: this?.toString()?.toDouble()

val Value.string: String?
    get() = this?.toString()

val Value.boolean: Boolean?
    get() = (this as? Boolean) ?: this?.toString()?.toBoolean()

//data class Range(val from: Value, val to: Value) {
//    fun isFinite() = from.number?.toDouble()?.isFinite() ?: false  && to.number?.toDouble()?.isFinite() ?: false
//}