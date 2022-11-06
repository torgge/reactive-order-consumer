package org.bonespirito.domain.message

import io.vertx.core.json.JsonObject

interface MessageConsumer {
    fun consume(message: JsonObject)
}
