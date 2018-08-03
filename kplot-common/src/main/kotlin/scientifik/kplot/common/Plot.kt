package scientifik.kplot.common


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
    operator fun get(axis:String, index: Int): Value = this[axis][index]

    /**
     * list of axes in this data container
     */
    val axes: List<String>

    /**
     * The number of values in each of axis set
     */
    val size: Int

    companion object {
        const val X_AXIS = "x"
        const val Y_AXIS = "y"
        const val Z_AXIS = "z"
    }
}

val PlotData.x: List<Value>
    get() = this[PlotData.X_AXIS]

val PlotData.y: List<Value>
    get() = this[PlotData.Y_AXIS]

val PlotData.z: List<Value>
    get() = this[PlotData.Z_AXIS]


interface Plot: PropertyHolder{
    val data: PlotData
}