package scientifik.kplot.common

import scientifik.kplot.common.config.Configuration
import scientifik.kplot.common.config.FrameConfiguration
import scientifik.kplot.common.config.Styleable

/**
 * A single displayed entity.
 */
interface Plot : Styleable {
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
interface PlotFrame : Styleable {
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
}