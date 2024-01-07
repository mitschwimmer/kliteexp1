package klite.sample

import TestResponse
import kotlinx.coroutines.runBlocking
import klite.json.JsonHttpClient
import klite.json.JsonMapper
import sampleServer
import kotlin.test.Test
import kotlin.test.expect

class ServerIntegrationTest {
    @Test fun requests() {
        val mapper = JsonMapper()
        val port = (Math.random() * 60000 + 1000).toInt()
        val server = sampleServer(port).apply { start(gracefulStopDelaySec = -1) }

        val http = JsonHttpClient("http://localhost:$port", registry = server.registry)
        runBlocking {
            expect(mapper.render(TestResponse("test"))) { http.get<String>("/api/json-test") }

        }

        server.stop()
    }
}