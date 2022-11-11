package org.bonespirito.application

import io.smallrye.mutiny.Multi
import org.bonespirito.domain.model.Order
import org.bonespirito.domain.payload.OrderPayload
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.jboss.logging.Logger
import java.util.concurrent.CompletionStage
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class OrderService {

    @Inject
    private lateinit var logger: Logger

    private var orders = mutableListOf<Order>()

    @Incoming("orders")
    fun consume(order: Message<OrderPayload?>): CompletionStage<Void?>? {
        // process your price.

        logger.info("Mensagem recebida $order")

        // Acknowledge the incoming message, marking the RabbitMQ message as `accepted`.
        return order.ack()
    }

    fun add(vo: Order) =
        orders.add(vo)

    fun multiOrders(): Multi<List<Order>> =
        Multi.createFrom()
            .items(orders as List<Order>)

    fun listOrders(): List<Order> = this.orders as List<Order>
}
