package scientifik.kplot

import scientifik.kplot.config.*

/**
 * Simple multiplatform implementations for [Configuration], [Plot] and [PlotData]
 */

/**
 * Basic implementation for a property holder
 */
class ConfigurationMap(private val map: MutableMap<String, Value> = HashMap()) : Configuration {
    private val listeners: MutableSet<PropertyChangeListener> = HashSet()

    override val keys: Collection<String> = map.keys

    /**
     * Apply "soft" path-name search meaning that element with name containing dots is returned as is, but if it is not found,
     * the search is delegated to the entry found by the first token separated by `.`. The use of `.` in names should be avoided
     */
    override fun get(key: String): Value = map[key] ?: run {
        (map[key.substringBefore(".")] as? ConfigurationMap)?.get(key.substringAfter("."))
    }

    /**
     * Use "hard" path-name, meaning than intermediate nodes are created for each segment between `.`
     */
    override fun set(key: String, value: Value) {
        if (key.contains(".")) {
            key.substringBefore(".") to {
                this[key.substringAfter(".")] = value
            }
        } else {
            map[key] = value
        }
        invalidate(key)
    }

    override fun String.to(meta: Configuration) {
        meta.onChange { key, _ ->
            invalidate("${this}.$key")
        }
        set(this, meta)
    }

    override fun onChange(listener: PropertyChangeListener) {
        listeners.add(listener)
    }

    override fun removeListener(listener: PropertyChangeListener) {
        listeners.remove(listener)
    }

    override fun invalidate(key: String?) {
        listeners.forEach { it.invoke(key, key?.let { key -> get(key) }) }
    }

    override fun empty(): Configuration = ConfigurationMap()
}

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

    override val size: Int
        get() = data.values.first().size

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
class SimplePlot(override val data: PlotData, meta: Configuration) : Plot {
    override val meta: StyledConfiguration = meta.asStyleable()
}

fun MutablePlotData.appendXY(x: Value, y: Value) = append(Plot.X_AXIS to x, Plot.Y_AXIS to y)

fun xyPlot(meta: Configuration = ConfigurationMap(), builder: MutablePlotData.() -> Unit): SimplePlot {
    return SimplePlot(SimplePlotDataBuilder(listOf(Plot.X_AXIS, Plot.Y_AXIS)).apply(builder), meta)
}