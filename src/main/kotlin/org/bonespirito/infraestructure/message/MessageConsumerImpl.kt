package org.bonespirito.infraestructure.message

import io.vertx.core.json.JsonObject
import org.bonespirito.application.OrderFacadeImpl
import org.bonespirito.domain.converters.toVO
import org.bonespirito.domain.message.MessageConsumer
import org.bonespirito.domain.payload.OrderPayload
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageConsumerImpl(
    private val facadeImpl: OrderFacadeImpl
) : MessageConsumer {
    override fun consume(message: JsonObject) {
        val orderPayload = message.mapTo(OrderPayload::class.java)
        val order = orderPayload.toVO()
        facadeImpl.process(order)
    }
}
