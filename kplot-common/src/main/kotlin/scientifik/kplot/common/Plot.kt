package scientifik.kplot.common

import scientifik.kplot.common.config.Configurable
import scientifik.kplot.common.config.Configuration
import scientifik.kplot.common.config.FrameConfiguration
import scientifik.kplot.common.config.Value


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


/**
 * A single displayed entity.
 */
interface Plot : Configurable {
    val data: PlotData
    //val type: String

    companion object {
        const val X_AXIS = "x"
        const val Y_AXIS = "y"
        const val Z_AXIS = "z"
    }
}

/**
 * A frame containing single or multiple plots
 */
interface PlotFrame : Configurable {
    /**
     * A configuration for the plot
     */
    val layout: FrameConfiguration
        get() = FrameConfiguration(meta)

    /**
     * Get existing plot or return null is it is not present
     */
    operator fun get(key: String): Plot?

    /**
     * put or override existing plot with given name
     */
    operator fun set(key: String, plot: Plot)

    /**
     * Remove the plot with given name
     */
    fun remove(key: String)

    /**
     * Apply configuration to plot each plot with name starting with [key]. Meaning that if there are plots with keys
     * `a.b.c` and `a.b.d` and key is `a.b`, both plots will bi updated
     */
    fun configure(key: String, meta: Configuration)

//    /**
//     * Append data to given plot. Not all plots allow this operation and [data] shape (axes names) must be compatible
//     */
//    fun append(key: String, data: PlotData)
}