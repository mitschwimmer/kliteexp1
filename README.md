# Klite Experiment 1
Is klite easy enough to understand, maintain and be creative with? Let`s try a server with a JSON API.

## Achievements
- Gradle 9 future proof test suite config

## Experiments
- [ ] Return JSON representation of a data class
- [ ] Validate Request Body and return an error in case of invalid request
- [ ] Integrate OTEL manual metric for request count
- [ ] Add labels for "URI" and "ClientVersion" to the metric just created
- [ ] Use OTEL for logs and traces

## OTEL
Open Telemetry provides a collector that can be used to test whether metrics, logs and traces are provided as expected.

https://opentelemetry.io/docs/instrumentation/java/exporters/#collector-setup

