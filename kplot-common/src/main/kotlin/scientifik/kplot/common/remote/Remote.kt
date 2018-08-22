package scientifik.kplot.common.remote

interface Remote{
    suspend fun respond(paramters: Map<String,String>, body: String): String
    //TODO add listeners for remote events?
}