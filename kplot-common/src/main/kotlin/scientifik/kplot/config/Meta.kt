package scientifik.kplot.config

interface Meta{

    /**
     * Get property for given key
     */
    operator fun get(key: String): Value


    /**
     * List top level keys for all registered properties
     */
    val keys: Collection<String>

}