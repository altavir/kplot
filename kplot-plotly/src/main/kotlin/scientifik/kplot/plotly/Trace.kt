package scientifik.kplot.plotly

import scientifik.kplot.Plot
import scientifik.kplot.config.*
import scientifik.kplot.x
import scientifik.kplot.y
import scientifik.kplot.z

class Trace(plot: Plot) : Plot by plot {
    private val traceConfig = TraceConfig(meta)

    val type: String get() = traceConfig.type

    val x: List<Value> get() = data.x
    val y: List<Value> get() = data.y
    val z: List<Value> get() = data.z
    val line: Meta get() = traceConfig.line
    val name: String? get() = traceConfig.name

//    var xy: Float32Array?
//    var xaxis: String?
//    var yaxis: String?
//    var text: dynamic /* String | Array<String> */
//    var marker: Any?
//    var mode: String? /* String /* "none" */ | String /* "lines" */ | String /* "markers" */ | String /* "text" */ | String /* "lines+markers" */ | String /* "text+markers" */ | String /* "text+lines" */ | String /* "text+lines+markers" */ */
//    var hoveron: String? /* String /* "points" */ | String /* "fills" */ */
//    var hoverinfo: String? /* String /* "x" */ | String /* "all" */ | String /* "none" */ | String /* "text" */ | String /* "name" */ | String /* "skip" */ | String /* "x+text" */ | String /* "x+name" */ | String /* "x+y" */ | String /* "x+y+text" */ | String /* "x+y+name" */ | String /* "x+y+z" */ | String /* "x+y+z+text" */ | String /* "x+y+z+name" */ | String /* "y+x" */ | String /* "y+x+text" */ | String /* "y+x+name" */ | String /* "y+z" */ | String /* "y+z+text" */ | String /* "y+z+name" */ | String /* "y+x+z" */ | String /* "y+x+z+text" */ | String /* "y+x+z+name" */ | String /* "z+x" */ | String /* "z+x+text" */ | String /* "z+x+name" */ | String /* "z+y+x" */ | String /* "z+y+x+text" */ | String /* "z+y+x+name" */ | String /* "z+x+y" */ | String /* "z+x+y+text" */ | String /* "z+x+y+name" */ */
//    var hoverlabel: Any?
//    var fill: String? /* String /* "none" */ | String /* "tozeroy" */ | String /* "tozerox" */ | String /* "tonexty" */ | String /* "tonextx" */ | String /* "toself" */ | String /* "tonext" */ */
//    var fillcolor: String?
//    var legendgroup: String?
//    var connectgaps: Boolean?
//    var visible: dynamic /* Boolean | String /* "legendonly" */ */
}

class TraceConfig(meta: Configuration) : Configuration by meta {
    var type by string(default = "scatted")
    var line: Configuration by child()
    var name by string()
}