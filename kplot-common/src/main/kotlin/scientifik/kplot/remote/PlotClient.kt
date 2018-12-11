package scientifik.kplot.remote

import hep.dataforge.meta.Meta
import hep.dataforge.meta.StyledConfig
import scientifik.kplot.Plot
import scientifik.kplot.PlotData
import scientifik.kplot.PlotFrame

/**
 * Client implementations for frame and plot. It is supposed that client controls data and configuration while actual plotting is performed on server
 */

/**
 * A remote client [PlotFrame]. The client controls the plotting but does not do rendering
 *
 * @param remote A remote that performs transactions
 * @param id and identification of the remote plot
 */
class ClientPlotFrame(val remote: Remote, val id: String) : PlotFrame {

    override fun get(key: String): Plot? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun set(key: String, plot: Plot) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(key: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        launch {
//            remote.respond(
//                    mapOf(
//                            "id" to id,
//                            "action" to "remove",
//                            "key" to key
//                    ),
//                    ""
//            )
//        }
    }

    override fun configure(key: String, meta: Meta) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val config: StyledConfig
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}

class RemotePlot(val remote: Remote) : Plot {
    override val data: PlotData
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val config: StyledConfig
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}