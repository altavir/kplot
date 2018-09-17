package scientifik.kplot

import hep.dataforge.meta.Value

/**
 * Generic scatter plot data. Data is organized in axis. Each axis consists a list of values of the same length.
 * Values could be nulls, which a treated as missing values.
 */
interface PlotData {
    /**
     * Produce a list of values alongside given axis
     */
    operator fun get(axis: String): List<Value>

    /**
     * Method to get specific value. Could be in some cases overridden for better performance
     */
    operator fun get(axis: String, index: Int): Value = this[axis][index]

    /**
     * list of axes in this data container
     */
    val axes: List<String>

    /**
     * The number of values in each of axis set
     */
    val size: Int
}

/**
 * Notifies about data change
 */
typealias DataChangeListener = PlotData.(Int) -> Unit

/**
 * A mutable [PlotData] with attachable listeners
 */
interface MutablePlotData : PlotData {

    fun append(map: Map<String, Value>)
    /**
     * Append data to the end of data list. Missing values are replaced by nulls
     */
    fun append(vararg pairs: Pair<String, Value>) {
        append(mapOf(*pairs))
    }

    /**
     * Replace data at index with given entry. Missing values are replaced by nulls
     */
    fun replace(index: Int, vararg pairs: Pair<String, Value>)

    /**
     * Remove entry at index and decrease index of subsequent entries
     */
    fun remove(index: Int)

    /**
     * Add change listener
     */
    fun onChange(listener: DataChangeListener)

    /**
     * Remove change listener
     */
    fun removeListener(listener: DataChangeListener)
}

/**
 * Extension property corresponding to x axis
 */
val PlotData.x: List<Value>
    get() = this[Plot.X_AXIS]

val PlotData.y: List<Value>
    get() = this[Plot.Y_AXIS]

val PlotData.z: List<Value>
    get() = this[Plot.Z_AXIS]

