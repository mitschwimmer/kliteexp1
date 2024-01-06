import klite.*
import klite.annotations.annotated

import klite.json.JsonBody

import kotlinx.coroutines.delay
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

    context("/hello") {
        get { "Hello World" }

        get("/delay") {
            delay(1000)
            "Waited for 1 sec"
        }

        get("/failure") { error("Failure") }

        post("/post") {
            data class JsonRequest(val required: String, val hello: String = "World")
            body<JsonRequest>()
        }

    }

    context("/api") {
        useOnly<JsonBody>() // in case only json should be supported in this context
        before(CorsHandler()) // enable CORS for this context, so that Swagger-UI can access the API
        useHashCodeAsETag() // automatically send 304 NotModified if request generates the same response as before
        annotated<MyRoutes>() // read routes from an annotated class - such classes are easier to unit-test

    }
}