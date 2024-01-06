package klite.sample

import kotlinx.coroutines.runBlocking
import klite.json.JsonHttpClient
import sampleServer
import kotlin.test.Test
import kotlin.test.expect

class ServerIntegrationTest {
    @Test fun requests() {
        val port = (Math.random() * 60000 + 1000).toInt()
        val server = sampleServer(port).apply { start(gracefulStopDelaySec = -1) }

        val http = JsonHttpClient("http://localhost:$port", registry = server.registry)
        runBlocking {
            expect("Hello World") { http.get<String>("/hello") }

        }

        server.stop()
    }
}