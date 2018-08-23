package scientifik.kplot.config

import kotlin.reflect.KClass

internal actual fun <E : Enum<E>> strToEnum(type: KClass<out E>, name: String): E {
    return java.lang.Enum.valueOf(type.java as Class<E>, name)
}