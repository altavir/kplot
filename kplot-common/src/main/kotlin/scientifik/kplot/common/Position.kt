package scientifik.kplot.common

import kotlin.properties.Delegates

typealias PositionListener = (Position) -> Unit

/**
 * A position of a point on the screen (Not inside plot)
 * @param x x coordinate in pixels
 * @param y y coordinate in pixels
 * @param parent parent position relative to which this position is calculated
 */
class Position(xRelative: Double, yRelative: Double, val parent: Position? = null) {

    private val listeners = HashSet<PositionListener>()

    var xRelative by Delegates.observable(xRelative){_,_,_->
        listeners.forEach { it.invoke(this) }
    }

    var yRelative  by Delegates.observable(yRelative){_,_,_->
        listeners.forEach { it.invoke(this) }
    }

    var x: Double
        get() = (parent?.x ?: 0.0) + xRelative
        set(value){
            xRelative = value - (parent?.x ?: 0.0)
        }

    var y: Double
        get() = (parent?.y ?: 0.0) + yRelative
        set(value){
            yRelative = value - (parent?.y ?: 0.0)
        }


    init {
        parent?.onChange { _ ->
            listeners.forEach { it.invoke(this) }
        }
    }

    fun onChange(listener: PositionListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: PositionListener) {
        listeners.remove(listener)
    }
}

