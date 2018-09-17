package scientifik.kplot

import hep.dataforge.meta.*

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
                data[it]?.add(map[it] ?: Null)
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
class SimplePlot(override val data: PlotData, meta: Config, style: Meta = EmptyMeta) : Plot {
    override val config: StyledConfig = meta.withStyle(style)
}

fun MutablePlotData.appendXY(x: Any?, y: Any?) = append(Plot.X_AXIS to Value.of(x), Plot.Y_AXIS to Value.of(y))

fun xyPlot(config: Config = Config(), style: Meta = EmptyMeta, builder: MutablePlotData.() -> Unit): SimplePlot {
    return SimplePlot(SimplePlotDataBuilder(listOf(Plot.X_AXIS, Plot.Y_AXIS)).apply(builder), config, style)
}