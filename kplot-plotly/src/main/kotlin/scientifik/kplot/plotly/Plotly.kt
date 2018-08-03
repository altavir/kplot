@file:Suppress(
        "UNUSED",
        "INTERFACE_WITH_SUPERCLASS",
        "OVERRIDING_FINAL_MEMBER",
        "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
        "CONFLICTING_OVERLOADS",
        "EXTERNAL_DELEGATION",
        "NESTED_CLASS_IN_EXTERNAL_INTERFACE"
)

/**
 * Plotly deffinition generated by ts2kt and manually editted
 */

import org.khronos.webgl.Float32Array
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import org.w3c.dom.events.MouseEvent
import kotlin.js.Promise

external object Plotly {
    fun newPlot(root: Element, data: List<ScatterData>, layout: Layout = definedExternally, config: Config = definedExternally): Promise<PlotlyHTMLElement>
    fun plot(root: Element, data: List<ScatterData>, layout: Layout = definedExternally, config: Config = definedExternally): Promise<PlotlyHTMLElement>
    fun relayout(root: Element, layout: Layout): Promise<PlotlyHTMLElement>
//    fun redraw(root: Element): Promise<PlotlyHTMLElement>
//    fun purge(root: Element);
//    fun restyle(root: Element, aobj: ScatterData, traces: List<Number> = definedExternally): Promise<PlotlyHTMLElement>
//    fun update(root: Element, traceUpdate: ScatterData, layoutUpdate: Layout, traces?: number[] | number): Promise<PlotlyHTMLElement>;
//    fun addTraces(root: Element, traces: Data | Data[], newIndices?: number[] | number): Promise<PlotlyHTMLElement>;
//    fun deleteTraces(root: Element, indices: number[] | number): Promise<PlotlyHTMLElement>;
//    fun moveTraces(root: Element, currentIndices: number[] | number, newIndices?: number[] | number): Promise<PlotlyHTMLElement>;
//    fun extendTraces(root: Element, update: Data | Data[], indices: number | number[]): Promise<PlotlyHTMLElement>;
//    fun prependTraces(root: Element, update: Data | Data[], indices: number | number[]): Promise<PlotlyHTMLElement>;
//    fun toImage(root: Element, opts: ToImgopts): Promise<string>;
//    fun downloadImage(root: Element, opts: DownloadImgopts): Promise<string>;
//    fun react(root: Element, data: Data[], layout?: Partial<Layout>, config?: Partial<Config>): Promise<PlotlyHTMLElement>;
//    fun addFrames(root: Element, frames: Array<Partial<Frame>>): Promise<PlotlyHTMLElement>;
//    fun deleteFrames(root: Element, frames: number[]): Promise<PlotlyHTMLElement>;
}


external interface StaticPlots {
    fun resize(root: String)
    fun resize(root: HTMLElement)
}

fun Point(builder: Point.() -> Unit): Point {
    return object : Point {
        override var x: Number? = null
        override var y: Number? = null
        override var z: Number? = null
    }.apply(builder)
}

external interface Point {
    var x: Number?
    var y: Number?
    var z: Number?
}

external interface PlotScatterDataPoint {
    var curveNumber: Number
    var data: ScatterData
    var pointIndex: Number
    var pointNumber: Number
    var x: Number
    var xaxis: LayoutAxis
    var y: Number
    var yaxis: LayoutAxis
}

external interface PlotMouseEvent {
    var points: Array<PlotScatterDataPoint>
    var event: MouseEvent
}

external interface PlotCoordinate {
    var x: Number
    var y: Number
    var pointNumber: Number
}

external interface PlotSelectionEvent {
    var points: Array<PlotCoordinate>
}

external interface PlotAxis {
    var range: dynamic /* JsTuple<Number, Number> */
    var autorange: Boolean
}

external interface PlotScene {
    var center: Point
    var eye: Point
    var up: Point
}

external interface PlotRelayoutEvent {
    var xaxis: PlotAxis
    var yaxis: PlotAxis
    var scene: PlotScene
}

external interface ClickAnnotationEvent {
    var index: Number
    var annotation: Annotations
    var fullAnnotation: Annotations
    var event: MouseEvent
}

external interface `T$0` {
    var duration: Number
    var redraw: Boolean
}

external interface `T$1` {
    var frame: `T$0`
    var transition: Transition
}

external interface FrameAnimationEvent {
    var name: String
    var frame: Frame
    var animation: `T$1`
}

external interface LegendClickEvent {
    var event: MouseEvent
    var node: PlotlyHTMLElement
    var curveNumber: Number
    var expandedIndex: Number
    var data: Array<Any?>
    var layout: Any?
    var frames: Array<Frame>
    var config: Any?
    var fullData: Array<Any?>
    var fullLayout: Any?
}

external interface SliderChangeEvent {
    var slider: Slider
    var step: SliderStep
    var interaction: Boolean
    var previousActive: Number
}

external interface SliderStartEvent {
    var slider: Slider
}

external interface SliderEndEvent {
    var slider: Slider
    var step: SliderStep
}

external interface BeforePlotEvent {
    var data: Array<Any?>
    var layout: Any?
    var config: Any?
}

external interface PlotlyHTMLElement : HTMLElement {
    fun on(event: String /* "plotly_click" */, callback: (event: PlotMouseEvent) -> Unit)
    fun on(event: String /* "plotly_hover" */, callback: (event: PlotMouseEvent) -> Unit)
    fun on(event: String /* "plotly_unhover" */, callback: (event: PlotMouseEvent) -> Unit)
    fun on(event: String /* "plotly_selecting" */, callback: (event: PlotSelectionEvent) -> Unit)
    fun on(event: String /* "plotly_selected" */, callback: (event: PlotSelectionEvent) -> Unit)
    fun on(event: String /* "plotly_restyle" */, callback: (data: dynamic /* JsTuple<Any, Array<Number>> */) -> Unit)
    fun on(event: String /* "plotly_relayout" */, callback: (event: PlotRelayoutEvent) -> Unit)
    fun on(event: String /* "plotly_clickannotation" */, callback: (event: ClickAnnotationEvent) -> Unit)
    fun on(event: String /* "plotly_animatingframe" */, callback: (event: FrameAnimationEvent) -> Unit)
    fun on(event: String /* "plotly_legendclick" */, callback: (event: LegendClickEvent) -> Boolean)
    fun on(event: String /* "plotly_legenddoubleclick" */, callback: (event: LegendClickEvent) -> Boolean)
    fun on(event: String /* "plotly_sliderchange" */, callback: (event: SliderChangeEvent) -> Unit)
    fun on(event: String /* "plotly_sliderend" */, callback: (event: SliderEndEvent) -> Unit)
    fun on(event: String /* "plotly_sliderstart" */, callback: (event: SliderStartEvent) -> Unit)
    fun on(event: String /* "plotly_event" */, callback: (data: Any) -> Unit)
    fun on(event: String /* "plotly_beforeplot" */, callback: (event: BeforePlotEvent) -> Boolean)
    fun on(event: dynamic /* String /* "plotly_afterexport" */ | String /* "plotly_afterplot" */ | String /* "plotly_animated" */ | String /* "plotly_animationinterrupted" */ | String /* "plotly_autosize" */ | String /* "plotly_beforeexport" */ | String /* "plotly_deselect" */ | String /* "plotly_doubleclick" */ | String /* "plotly_framework" */ | String /* "plotly_redraw" */ | String /* "plotly_transitioning" */ | String /* "plotly_transitioninterrupted" */ */, callback: () -> Unit)
}

external interface ToImgopts {
    var format: dynamic /* String /* "jpeg" */ | String /* "png" */ | String /* "webp" */ | String /* "svg" */ */
    var width: Number
    var height: Number
}

external interface DownloadImgopts {
    var format: dynamic /* String /* "jpeg" */ | String /* "png" */ | String /* "webp" */ | String /* "svg" */ */
    var width: Number
    var height: Number
    var filename: String
}

external interface Layout {
    var title: String
    var titlefont: Any?
    var autosize: Boolean
    var showlegend: Boolean
    var paper_bgcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var plot_bgcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var separators: String
    var hidesources: Boolean
    var xaxis: Any?
    var yaxis: Any?
    var yaxis2: Any?
    var yaxis3: Any?
    var yaxis4: Any?
    var yaxis5: Any?
    var yaxis6: Any?
    var yaxis7: Any?
    var yaxis8: Any?
    var yaxis9: Any?
    var margin: Any?
    var height: Number
    var width: Number
    var hovermode: dynamic /* Boolean | String /* "closest" */ | String /* "x" */ | String /* "y" */ */
    var hoverlabel: Any?
    var calendar: dynamic /* String /* "gregorian" */ | String /* "chinese" */ | String /* "coptic" */ | String /* "discworld" */ | String /* "ethiopian" */ | String /* "hebrew" */ | String /* "islamic" */ | String /* "julian" */ | String /* "mayan" */ | String /* "nanakshahi" */ | String /* "nepali" */ | String /* "persian" */ | String /* "jalali" */ | String /* "taiwan" */ | String /* "thai" */ | String /* "ummalqura" */ */
    var ternary: Any
    var geo: Any
    var mapbox: Any
    var radialaxis: Any
    var angularaxis: Any
    var direction: dynamic /* String /* "clockwise" */ | String /* "counterclockwise" */ */
    var dragmode: dynamic /* String /* "zoom" */ | String /* "pan" */ | String /* "select" */ | String /* "lasso" */ | String /* "orbit" */ | String /* "turntable" */ */
    var orientation: Number
    var annotations: Array<Any?>
    var shapes: Array<Any?>
    var images: Array<Any?>
    var updatemenus: Any
    var sliders: Array<Any?>
    var legend: Any?
    var font: Any?
    var scene: Any?
}

external interface Legend : Label {
    var traceorder: dynamic /* String /* "grouped" */ | String /* "normal" */ | String /* "reversed" */ */
    var x: Number
    var y: Number
    var borderwidth: Number
    var orientation: dynamic /* String /* "v" */ | String /* "h" */ */
    var tracegroupgap: Number
    var xanchor: dynamic /* String /* "auto" */ | String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var yanchor: dynamic /* String /* "auto" */ | String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
}

external interface Axis {
    var visible: Boolean
    var color: dynamic /* String | Array<String> | Array<Array<String>> */
    var title: String
    var titlefont: Any?
    var type: dynamic /* String /* "-" */ | String /* "linear" */ | String /* "log" */ | String /* "date" */ | String /* "category" */ */
    var autorange: dynamic /* Boolean | String /* "reversed" */ */
    var rangemode: dynamic /* String /* "normal" */ | String /* "tozero" */ | String /* "nonnegative" */ */
    var range: Array<Any>
    var tickmode: dynamic /* String /* "auto" */ | String /* "linear" */ | String /* "array" */ */
    var nticks: Number
    var tick0: dynamic /* String | Number */
    var dtick: dynamic /* String | Number */
    var tickvals: Array<Any>
    var ticktext: Array<String>
    var ticks: dynamic /* String /* "" */ | String /* "outside" */ | String /* "inside" */ */
    var mirror: dynamic /* Boolean | String /* "ticks" */ | String /* "all" */ | String /* "allticks" */ */
    var ticklen: Number
    var tickwidth: Number
    var tickcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var showticklabels: Boolean
    var showspikes: Boolean
    var spikecolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var spikethickness: Number
    var categoryorder: dynamic /* String /* "array" */ | String /* "trace" */ | String /* "category ascending" */ | String /* "category descending" */ */
    var categoryarray: Array<Any>
    var tickfont: Any?
    var tickangle: Number
    var tickprefix: String
    var showtickprefix: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var ticksuffix: String
    var showticksuffix: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var showexponent: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var exponentformat: dynamic /* String /* "none" */ | String /* "e" */ | String /* "E" */ | String /* "power" */ | String /* "SI" */ | String /* "B" */ */
    var separatethousands: Boolean
    var tickformat: String
    var hoverformat: String
    var showline: Boolean
    var linecolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var linewidth: Number
    var showgrid: Boolean
    var gridcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var gridwidth: Number
    var zeroline: Boolean
    var zerolinecolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var zerolinewidth: Number
    var calendar: dynamic /* String /* "gregorian" */ | String /* "chinese" */ | String /* "coptic" */ | String /* "discworld" */ | String /* "ethiopian" */ | String /* "hebrew" */ | String /* "islamic" */ | String /* "julian" */ | String /* "mayan" */ | String /* "nanakshahi" */ | String /* "nepali" */ | String /* "persian" */ | String /* "jalali" */ | String /* "taiwan" */ | String /* "thai" */ | String /* "ummalqura" */ */
}

external interface LayoutAxis : Axis {
    var fixedrange: Boolean
    var scaleanchor: dynamic /* String /* "/^x([2-9]|[1-9][0-9]+)?$/" */ | String /* "/^y([2-9]|[1-9][0-9]+)?$/" */ */
    var scaleratio: Number
    var constrain: dynamic /* String /* "range" */ | String /* "domain" */ */
    var constraintoward: dynamic /* String /* "left" */ | String /* "center" */ | String /* "right" */ | String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var spikedash: String
    var spikemode: String
    var anchor: dynamic /* String /* "/^x([2-9]|[1-9][0-9]+)?$/" */ | String /* "/^y([2-9]|[1-9][0-9]+)?$/" */ | String /* "free" */ */
    var side: dynamic /* String /* "left" */ | String /* "right" */ | String /* "top" */ | String /* "bottom" */ */
    var overlaying: dynamic /* String /* "/^x([2-9]|[1-9][0-9]+)?$/" */ | String /* "/^y([2-9]|[1-9][0-9]+)?$/" */ | String /* "free" */ */
    var layer: dynamic /* String /* "above traces" */ | String /* "below traces" */ */
    var domain: Array<Number>
    var position: Number
    var rangeslider: Any?
    var rangeselector: Any?
}

external interface SceneAxis : Axis {
    var spikesides: Boolean
    var showbackground: Boolean
    var backgroundcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var showaxeslabels: Boolean
}

external interface ShapeLine {
    var color: String
    var width: Number
    var dash: dynamic /* String /* "solid" */ | String /* "dot" */ | String /* "dash" */ | String /* "longdash" */ | String /* "dashdot" */ | String /* "longdashdot" */ */
}

external interface Shape {
    var visible: Boolean
    var layer: dynamic /* String /* "below" */ | String /* "above" */ */
    var type: dynamic /* String /* "rect" */ | String /* "circle" */ | String /* "line" */ | String /* "path" */ */
    var path: String
    var xref: dynamic /* String /* "x" */ | String /* "paper" */ */
    var yref: dynamic /* String /* "y" */ | String /* "paper" */ */
    var x0: dynamic /* String | Number | Date | Nothing? */
    var y0: dynamic /* String | Number | Date | Nothing? */
    var x1: dynamic /* String | Number | Date | Nothing? */
    var y1: dynamic /* String | Number | Date | Nothing? */
    var fillcolor: String
    var opacity: Number
    var line: Any?
}

external interface Margin {
    var t: Number
    var b: Number
    var l: Number
    var r: Number
}

external interface Icon {
    var width: Number
    var path: String
    var ascent: Number
    var descent: Number
}

external interface ModeBarButton {
    var name: String
    var title: String
    var icon: dynamic /* String | Icon */
    var gravity: String? get() = definedExternally; set(value) = definedExternally
    var click: (gd: PlotlyHTMLElement, ev: MouseEvent) -> Unit
    var attr: String? get() = definedExternally; set(value) = definedExternally
    var `val`: Any? get() = definedExternally; set(value) = definedExternally
    var toggle: Boolean? get() = definedExternally; set(value) = definedExternally
}


fun ScatterData(builder: ScatterData.() -> Unit): ScatterData {
    return object: ScatterData{
        override var type: String = "scatter"
        override var x: List<Any> = emptyList()
        override var y: List<Any> = emptyList()
        override var z: List<Any> = emptyList()
        override var xy: Float32Array? = null
        override var xaxis: String? = null
        override var yaxis: String? = null
        override var text: dynamic = null
        override var line: Any? = null
        override var marker: Any? = null
        override var mode: String? = null
        override var hoveron: String? = null
        override var hoverinfo: String? = null
        override var hoverlabel: Any? = null
        override var fill: String? = null
        override var fillcolor: String? = null
        override var legendgroup: String? = null
        override var name: String? = null
        override var connectgaps: Boolean? = null
        override var visible: dynamic = null
    }.apply(builder)
}

external interface ScatterData {
    //TODO replace by enum
    var type: String /* String /* "bar" */ | String /* "pointcloud" */ | String /* "scatter" */ | String /* "scattergl" */ | String /* "scatter3d" */ | String /* "surface" */ */
    var x: List<Any>
    var y: List<Any>
    var z: List<Any>
    var xy: Float32Array?
    var xaxis: String?
    var yaxis: String?
    var text: dynamic /* String | Array<String> */
    var line: Any?
    var marker: Any?
    var mode: String? /* String /* "none" */ | String /* "lines" */ | String /* "markers" */ | String /* "text" */ | String /* "lines+markers" */ | String /* "text+markers" */ | String /* "text+lines" */ | String /* "text+lines+markers" */ */
    var hoveron: String? /* String /* "points" */ | String /* "fills" */ */
    var hoverinfo: String? /* String /* "x" */ | String /* "all" */ | String /* "none" */ | String /* "text" */ | String /* "name" */ | String /* "skip" */ | String /* "x+text" */ | String /* "x+name" */ | String /* "x+y" */ | String /* "x+y+text" */ | String /* "x+y+name" */ | String /* "x+y+z" */ | String /* "x+y+z+text" */ | String /* "x+y+z+name" */ | String /* "y+x" */ | String /* "y+x+text" */ | String /* "y+x+name" */ | String /* "y+z" */ | String /* "y+z+text" */ | String /* "y+z+name" */ | String /* "y+x+z" */ | String /* "y+x+z+text" */ | String /* "y+x+z+name" */ | String /* "z+x" */ | String /* "z+x+text" */ | String /* "z+x+name" */ | String /* "z+y+x" */ | String /* "z+y+x+text" */ | String /* "z+y+x+name" */ | String /* "z+x+y" */ | String /* "z+x+y+text" */ | String /* "z+x+y+name" */ */
    var hoverlabel: Any?
    var fill: String? /* String /* "none" */ | String /* "tozeroy" */ | String /* "tozerox" */ | String /* "tonexty" */ | String /* "tonextx" */ | String /* "toself" */ | String /* "tonext" */ */
    var fillcolor: String?
    var legendgroup: String?
    var name: String?
    var connectgaps: Boolean?
    var visible: dynamic /* Boolean | String /* "legendonly" */ */
}

external interface `T$2` {
    var dtickrange: Array<Any>
    var value: String
}

external interface `T$3` {
    var thicknessmode: dynamic /* String /* "fraction" */ | String /* "pixels" */ */
    var thickness: Number
    var lenmode: dynamic /* String /* "fraction" */ | String /* "pixels" */ */
    var len: Number
    var x: Number
    var xanchor: dynamic /* String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var xpad: Number
    var y: Number
    var yanchor: dynamic /* String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var ypad: Number
    var outlinecolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var outlinewidth: Number
    var bordercolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var borderwidth: dynamic /* String | Array<String> | Array<Array<String>> */
    var bgcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var tickmode: dynamic /* String /* "auto" */ | String /* "linear" */ | String /* "array" */ */
    var nticks: Number
    var tick0: dynamic /* String | Number */
    var dtick: dynamic /* String | Number */
    var tickvals: dynamic /* Array<dynamic /* String | Number | Date | Nothing? */> | Array<Array<dynamic /* String | Number | Date | Nothing? */>> | Int8Array | Uint8Array | Int16Array | Uint16Array | Int32Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | Array<Array<Array<dynamic /* String | Number | Date | Nothing? */>>> */
    var ticktext: dynamic /* Array<dynamic /* String | Number | Date | Nothing? */> | Array<Array<dynamic /* String | Number | Date | Nothing? */>> | Int8Array | Uint8Array | Int16Array | Uint16Array | Int32Array | Uint32Array | Uint8ClampedArray | Float32Array | Float64Array | Array<Array<Array<dynamic /* String | Number | Date | Nothing? */>>> */
    var ticks: dynamic /* String /* "" */ | String /* "outside" */ | String /* "inside" */ */
    var ticklen: Number
    var tickwidth: Number
    var tickcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var showticklabels: Boolean
    var tickfont: Font
    var tickangle: Number
    var tickformat: String
    var tickformatstops: `T$2`
    var tickprefix: String
    var showtickprefix: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var ticksuffix: String
    var showticksuffix: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var separatethousands: Boolean
    var exponentformat: dynamic /* String /* "none" */ | String /* "e" */ | String /* "E" */ | String /* "power" */ | String /* "SI" */ | String /* "B" */ */
    var showexponent: dynamic /* String /* "all" */ | String /* "first" */ | String /* "last" */ | String /* "none" */ */
    var title: String
    var titlefont: Font
    var titleside: dynamic /* String /* "right" */ | String /* "top" */ | String /* "bottom" */ */
    var tickvalssrc: Any
    var ticktextsrc: Any
}

external interface `T$4` {
    var type: dynamic /* String /* "none" */ | String /* "radial" */ | String /* "horizontal" */ | String /* "vertical" */ */
    var color: dynamic /* String | Array<String> | Array<Array<String>> */
    var typesrc: Any
    var colorsrc: Any
}

external interface ScatterMarker {
    var symbol: dynamic /* String | Array<String> */
    var color: dynamic /* String | Array<Number> | Array<String> | Array<Array<String>> */
    var colorscale: dynamic /* String | Array<String> | Array<Array<dynamic /* String | Number */>> */
    var cauto: Boolean
    var cmax: Number
    var cmin: Number
    var autocolorscale: Boolean
    var reversescale: Boolean
    var opacity: dynamic /* Number | Array<Number> */
    var size: dynamic /* Number | Array<Number> */
    var maxdisplayed: Number
    var sizeref: Number
    var sizemax: Number
    var sizemin: Number
    var sizemode: dynamic /* String /* "diameter" */ | String /* "area" */ */
    var showscale: Boolean
    var line: Any?
    var colorbar: `T$3`
    var gradient: `T$4`
}

external interface ScatterMarkerLine {
    var width: dynamic /* Number | Array<Number> */
    var color: dynamic /* String | Array<String> | Array<Array<String>> */
    var colorscale: dynamic /* String | Array<String> */
    var cauto: Boolean
    var cmax: Number
    var cmin: Number
    var autocolorscale: Boolean
    var reversescale: Boolean
}

external interface ScatterLine {
    var color: dynamic /* String | Array<String> | Array<Array<String>> */
    var width: Number
    var dash: dynamic /* String /* "solid" */ | String /* "dot" */ | String /* "dash" */ | String /* "longdash" */ | String /* "dashdot" */ | String /* "longdashdot" */ */
    var shape: dynamic /* String /* "linear" */ | String /* "spline" */ | String /* "hv" */ | String /* "vh" */ | String /* "hvh" */ | String /* "vhv" */ */
    var smoothing: Number
    var simplify: Boolean
}

external interface Font {
    var family: String
    var size: Number
    var color: dynamic /* String | Array<String> | Array<Array<String>> */
}

external interface Edits {
    var annotationPosition: Boolean
    var annotationTail: Boolean
    var annotationText: Boolean
    var axisTitleText: Boolean
    var colorbarPosition: Boolean
    var colorbarTitleText: Boolean
    var legendPosition: Boolean
    var legendText: Boolean
    var shapePosition: Boolean
    var titleText: Boolean
}

external interface Config {
    var staticPlot: Boolean
    var editable: Boolean
    var edits: Any?
    var autosizable: Boolean
    var queueLength: Number
    var fillFrame: Boolean
    var frameMargins: Number
    var scrollZoom: Boolean
    var doubleClick: dynamic /* Boolean | String /* "reset+autosize" */ | String /* "reset" */ | String /* "autosize" */ */
    var showTips: Boolean
    var showAxisDragHandles: Boolean
    var showAxisRangeEntryBoxes: Boolean
    var showLink: Boolean
    var sendData: Boolean
    var linkText: String
    var showSources: Boolean
    var displayModeBar: dynamic /* Boolean | String /* "hover" */ */
    var modeBarButtonsToRemove: Array<dynamic /* String /* "lasso2d" */ | String /* "select2d" */ | String /* "sendDataToCloud" */ | String /* "autoScale2d" */ | String /* "zoom2d" */ | String /* "pan2d" */ | String /* "zoomIn2d" */ | String /* "zoomOut2d" */ | String /* "resetScale2d" */ | String /* "hoverClosestCartesian" */ | String /* "hoverCompareCartesian" */ | String /* "zoom3d" */ | String /* "pan3d" */ | String /* "orbitRotation" */ | String /* "tableRotation" */ | String /* "resetCameraDefault3d" */ | String /* "resetCameraLastSave3d" */ | String /* "hoverClosest3d" */ | String /* "zoomInGeo" */ | String /* "zoomOutGeo" */ | String /* "resetGeo" */ | String /* "hoverClosestGeo" */ | String /* "hoverClosestGl2d" */ | String /* "hoverClosestPie" */ | String /* "toggleHover" */ | String /* "toImage" */ | String /* "resetViews" */ | String /* "toggleSpikelines" */ */>
    var modeBarButtonsToAdd: dynamic /* Array<dynamic /* String /* "lasso2d" */ | String /* "select2d" */ | String /* "sendDataToCloud" */ | String /* "autoScale2d" */ | String /* "zoom2d" */ | String /* "pan2d" */ | String /* "zoomIn2d" */ | String /* "zoomOut2d" */ | String /* "resetScale2d" */ | String /* "hoverClosestCartesian" */ | String /* "hoverCompareCartesian" */ | String /* "zoom3d" */ | String /* "pan3d" */ | String /* "orbitRotation" */ | String /* "tableRotation" */ | String /* "resetCameraDefault3d" */ | String /* "resetCameraLastSave3d" */ | String /* "hoverClosest3d" */ | String /* "zoomInGeo" */ | String /* "zoomOutGeo" */ | String /* "resetGeo" */ | String /* "hoverClosestGeo" */ | String /* "hoverClosestGl2d" */ | String /* "hoverClosestPie" */ | String /* "toggleHover" */ | String /* "toImage" */ | String /* "resetViews" */ | String /* "toggleSpikelines" */ */> | Array<ModeBarButton> */
    var modeBarButtons: dynamic /* Boolean | Array<Array<dynamic /* String /* "lasso2d" */ | String /* "select2d" */ | String /* "sendDataToCloud" */ | String /* "autoScale2d" */ | String /* "zoom2d" */ | String /* "pan2d" */ | String /* "zoomIn2d" */ | String /* "zoomOut2d" */ | String /* "resetScale2d" */ | String /* "hoverClosestCartesian" */ | String /* "hoverCompareCartesian" */ | String /* "zoom3d" */ | String /* "pan3d" */ | String /* "orbitRotation" */ | String /* "tableRotation" */ | String /* "resetCameraDefault3d" */ | String /* "resetCameraLastSave3d" */ | String /* "hoverClosest3d" */ | String /* "zoomInGeo" */ | String /* "zoomOutGeo" */ | String /* "resetGeo" */ | String /* "hoverClosestGeo" */ | String /* "hoverClosestGl2d" */ | String /* "hoverClosestPie" */ | String /* "toggleHover" */ | String /* "toImage" */ | String /* "resetViews" */ | String /* "toggleSpikelines" */ */>> | Array<Array<ModeBarButton>> */
    var displaylogo: Boolean
    var plotGlPixelRatio: Number
    var setBackground: dynamic /* String | String /* "opaque" */ | String /* "transparent" */ */
    var topojsonURL: String
    var mapboxAccessToken: String
    var logging: dynamic /* Boolean | Number /* 0 */ | Number /* 1 */ | Number /* 2 */ */
    var globalTransforms: Array<Any>
    var locale: String
}

external interface RangeSlider {
    var visible: Boolean
    var thickness: Number
    var range: dynamic /* JsTuple<dynamic /* String | Number | Date | Nothing? */, dynamic /* String | Number | Date | Nothing? */> */
    var borderwidth: Number
    var bordercolor: String
    var bgcolor: String
}

external interface RangeSelectorButton {
    var step: dynamic /* String /* "all" */ | String /* "second" */ | String /* "minute" */ | String /* "hour" */ | String /* "day" */ | String /* "month" */ | String /* "year" */ */
    var stepmode: dynamic /* String /* "backward" */ | String /* "todate" */ */
    var count: Number
    var label: String
}

external interface RangeSelector : Label {
    var buttons: Array<Any?>
    var visible: Boolean
    var x: Number
    var xanchor: dynamic /* String /* "auto" */ | String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var y: Number
    var yanchor: dynamic /* String /* "auto" */ | String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var activecolor: String
    var borderwidth: Number
}

external interface Camera {
    var up: Any?
    var center: Any?
    var eye: Any?
}

external interface Label {
    var bgcolor: String
    var bordercolor: String
    var font: Any?
}

external interface Annotations : Label {
    var visible: Boolean
    var text: String
    var textangle: String
    var width: Number
    var height: Number
    var opacity: Number
    var align: dynamic /* String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var valign: dynamic /* String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var borderpad: Number
    var borderwidth: Number
    var showarrow: Boolean
    var arrowcolor: String
    var arrowhead: Number
    var startarrowhead: Number
    var arrowside: dynamic /* String /* "end" */ | String /* "start" */ */
    var arrowsize: Number
    var startarrowsize: Number
    var arrowwidth: Number
    var standoff: Number
    var startstandoff: Number
    var ax: Number
    var ay: Number
    var axref: String /* "pixel" */
    var ayref: String /* "pixel" */
    var xref: dynamic /* String /* "x" */ | String /* "paper" */ */
    var x: dynamic /* String | Number */
    var xanchor: dynamic /* String /* "auto" */ | String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var xshift: Number
    var yref: dynamic /* String /* "y" */ | String /* "paper" */ */
    var y: dynamic /* String | Number */
    var yanchor: dynamic /* String /* "auto" */ | String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var yshift: Number
    var clicktoshow: dynamic /* Boolean | String /* "onoff" */ | String /* "onout" */ */
    var xclick: Any
    var yclick: Any
    var hovertext: String
    var hoverlabel: Any?
    var captureevents: Boolean
}

external interface Image {
    var visible: Boolean
    var source: String
    var layer: dynamic /* String /* "below" */ | String /* "above" */ */
    var sizex: Number
    var sizey: Number
    var sizing: dynamic /* String /* "fill" */ | String /* "contain" */ | String /* "stretch" */ */
    var opacity: Number
    var x: dynamic /* String | Number */
    var y: dynamic /* String | Number */
    var xanchor: dynamic /* String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var yanchor: dynamic /* String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var xref: dynamic /* String /* "x" */ | String /* "paper" */ */
    var yref: dynamic /* String /* "y" */ | String /* "paper" */ */
}

external interface Scene {
    var bgcolor: String
    var camera: Any?
    var domain: Any?
    var aspectmode: dynamic /* String /* "auto" */ | String /* "cube" */ | String /* "data" */ | String /* "manual" */ */
    var aspectratio: Any?
    var xaxis: Any?
    var yaxis: Any?
    var zaxis: Any?
    var dragmode: dynamic /* Boolean | String /* "zoom" */ | String /* "pan" */ | String /* "orbit" */ | String /* "turntable" */ */
    var hovermode: dynamic /* Boolean | String /* "closest" */ */
    var annotations: dynamic /* Any? | Array<Any?> */
    var captureevents: Boolean
}

external interface Domain {
    var x: Array<Number>
    var y: Array<Number>
}

external interface Frame {
    var group: String
    var name: String
    var traces: Array<Number>
    var baseframe: String
    var data: Array<Any?>
    var layout: Any?
}

external interface Transition {
    var duration: Number
    var easing: dynamic /* String /* "linear" */ | String /* "circle" */ | String /* "quad" */ | String /* "cubic" */ | String /* "sin" */ | String /* "exp" */ | String /* "elastic" */ | String /* "back" */ | String /* "bounce" */ | String /* "linear-in" */ | String /* "quad-in" */ | String /* "cubic-in" */ | String /* "sin-in" */ | String /* "exp-in" */ | String /* "circle-in" */ | String /* "elastic-in" */ | String /* "back-in" */ | String /* "bounce-in" */ | String /* "linear-out" */ | String /* "quad-out" */ | String /* "cubic-out" */ | String /* "sin-out" */ | String /* "exp-out" */ | String /* "circle-out" */ | String /* "elastic-out" */ | String /* "back-out" */ | String /* "bounce-out" */ | String /* "linear-in-out" */ | String /* "quad-in-out" */ | String /* "cubic-in-out" */ | String /* "sin-in-out" */ | String /* "exp-in-out" */ | String /* "circle-in-out" */ | String /* "elastic-in-out" */ | String /* "back-in-out" */ | String /* "bounce-in-out" */ */
}

external interface SliderStep {
    var visible: Boolean
    var method: dynamic /* String /* "skip" */ | String /* "animate" */ | String /* "relayout" */ | String /* "restyle" */ | String /* "update" */ */
    var args: Array<Any>
    var label: String
    var value: String
    var execute: Boolean
}

external interface Padding {
    var t: Number
    var r: Number
    var b: Number
    var l: Number
    var editType: String /* "arraydraw" */
}

external interface `T$5` {
    var visible: Boolean
    var xanchor: dynamic /* String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var offset: Number
    var prefix: String
    var suffix: String
    var font: Any?
}

external interface Slider {
    var visible: Boolean
    var active: Number
    var steps: Array<Any?>
    var lenmode: dynamic /* String /* "fraction" */ | String /* "pixels" */ */
    var len: Number
    var x: Number
    var y: Number
    var pad: Any?
    var xanchor: dynamic /* String /* "auto" */ | String /* "left" */ | String /* "center" */ | String /* "right" */ */
    var yanchor: dynamic /* String /* "auto" */ | String /* "top" */ | String /* "middle" */ | String /* "bottom" */ */
    var transition: Transition
    var currentvalue: `T$5`
    var font: Font
    var activebgcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var bgcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var bordercolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var borderwidth: Number
    var ticklen: Number
    var tickcolor: dynamic /* String | Array<String> | Array<Array<String>> */
    var tickwidth: Number
    var minorticklen: Number
}
