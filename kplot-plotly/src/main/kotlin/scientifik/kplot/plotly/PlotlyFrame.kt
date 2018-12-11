package scientifik.kplot.plotly

import hep.dataforge.meta.Config
import hep.dataforge.meta.Meta
import hep.dataforge.meta.update
import hep.dataforge.meta.withStyle
import org.w3c.dom.Element
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import scientifik.kplot.Plot
import scientifik.kplot.PlotFrame
import kotlin.js.Promise

external object Plotly {
    fun react(root: Element, data: List<Trace>, layout: Config = definedExternally, config: Config = definedExternally): Promise<HTMLElement>;
}

class PlotlyFrame(val element: HTMLDivElement, meta: Config? = null) : PlotFrame {

    override val config = (meta ?: Config()).withStyle().apply {
        onChange { _, _, _ ->
            //TODO differentiate parameters?
            updateFrame()
        }
    }

    private val traces = HashMap<String, Trace>()

    private fun updateFrame() {
        Plotly.react(element, traces.values.toList(), layout.config)
    }

    override fun get(key: String): Plot? {
        return traces[key]
    }

    override fun set(key: String, plot: Plot) {
        traces[key]
        updateFrame()
    }

    override fun remove(key: String) {
        traces.remove(key)
        updateFrame()
    }

    override fun configure(key: String, meta: Meta) {
        get(key)?.let {
            it.config.update(meta)
            updateFrame()
        }
    }
}