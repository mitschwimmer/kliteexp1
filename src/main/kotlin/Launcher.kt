import klite.AssetsHandler
import klite.Config
import klite.Server
import klite.json.JsonBody
import klite.register
import java.net.InetSocketAddress
import java.net.http.HttpClient
import java.nio.file.Path
import java.time.Duration.ofSeconds

fun main() {
    sampleServer().start()
}

fun sampleServer(port: Int = 8080) = Server(listen = InetSocketAddress(port)).apply {
    Config.useEnvFile()
    use<JsonBody>() // enables parsing/sending of application/json requests/responses, depending on the Accept header

    assets("/", AssetsHandler(Path.of("public"), useIndexForUnknownPaths = true))

    register(HttpClient.newBuilder().connectTimeout(ofSeconds(5)).build())

    context("/api") {

        get("/json-test") { TestResponse(hello = "test") }

        get("/failure") { error("Failure") }

        post("/post") {
            data class JsonRequest(val required: String, val hello: String = "World")
            body<JsonRequest>()
        }

    }

}

data class TestResponse(val hello: String)