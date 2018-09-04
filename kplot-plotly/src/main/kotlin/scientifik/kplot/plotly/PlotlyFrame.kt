package scientifik.kplot.plotly

import org.w3c.dom.Element
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLElement
import scientifik.kplot.ConfigurationMap
import scientifik.kplot.Plot
import scientifik.kplot.PlotFrame
import scientifik.kplot.config.Configuration
import scientifik.kplot.config.asStyleable
import scientifik.kplot.config.update
import kotlin.js.Promise

external object Plotly {
    fun react(root: Element, data: List<Trace>, layout: Configuration = definedExternally, config: Configuration = definedExternally): Promise<HTMLElement>;
}

class PlotlyFrame(val element: HTMLDivElement, meta: Configuration? = null) : PlotFrame {

    override val meta = (meta ?: ConfigurationMap()).asStyleable().apply {
        onChange { _, _ ->
            //TODO differentiate parameters?
            updateFrame()
        }
    }

    private val traces = HashMap<String, Trace>()

    private fun updateFrame() {
        Plotly.react(element, traces.values.toList(), layout)
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

    override fun configure(key: String, meta: Configuration) {
        get(key)?.let {
            it.meta.update(meta)
            updateFrame()
        }
    }
}