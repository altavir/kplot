package scientifik.kplot.common

import scientifik.kplot.common.config.Config
import scientifik.kplot.common.config.ConfigMap
import scientifik.kplot.common.config.Value

/**
 * Simple multiplatform implementation for [Plot] and [PlotData]
 */

/**
 * Simple implementation of [PlotData]
 */
class SimplePlotData(private val data: Map<String, List<Value>>) : PlotData {

    override val size: Int = data.values.map { it.size }.distinct().apply {
        if (size > 1) {
            error("Dimension mismatch for columns")
        }
    }.first()

    override fun get(axis: String): List<Value> = data[axis]
            ?: throw IllegalStateException("Axis with name $axis not found")

    override val axes: List<String> = data.keys.toList()
}

/**
 * A builder for simple data
 */
class SimplePlotDataBuilder(override val axes: List<String>) : MutablePlotData {

    private val data = axes.associate { it to ArrayList<Value>() }

    private val listeners = HashSet<DataChangeListener>()

    override fun get(axis: String): List<Value> = data[axis]
            ?: throw IllegalStateException("Axis with name $axis not found")

    override var size: Int = 0
        private set

    override fun append(map: Map<String, Value>) {
        synchronized(this) {
            axes.forEach {
                data[it]?.add(map[it])
            }
        }
    }

    override fun replace(index: Int, vararg pairs: Pair<String, Value>) {
        synchronized(this) {
            pairs.forEach {
                data[it.first]?.set(index, it.second)
            }
        }
    }

    override fun remove(index: Int) {
        synchronized(this) {
            data.values.forEach { remove(index) }
        }
    }

    override fun onChange(listener: DataChangeListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: DataChangeListener) {
        listeners.remove(listener)
    }
}

/**
 * Simple implementation of [Plot]
 */
class SimplePlot(override val data: PlotData, override val config: Config) : Plot

fun MutablePlotData.appendXY(x: Value, y:Value) = append(Plot.X_AXIS to x, Plot.X_AXIS to y)

fun xyPlot(config: Config = ConfigMap(), builder: MutablePlotData.() -> Unit): SimplePlot {
    return SimplePlot(SimplePlotDataBuilder(listOf(Plot.X_AXIS, Plot.Y_AXIS)).apply(builder), config)
}