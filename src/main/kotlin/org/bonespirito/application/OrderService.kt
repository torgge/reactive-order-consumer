package org.bonespirito.application

import io.smallrye.mutiny.Multi
import org.bonespirito.domain.model.Order
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class OrderService {

    @Inject
    private lateinit var logger: Logger

    private var orders = mutableListOf<Order>()

    fun add(vo: Order) =
        orders.add(vo)

    fun multiOrders(): Multi<List<Order>> =
        Multi.createFrom()
            .items(orders as List<Order>)

    fun listOrders(): List<Order> = this.orders as List<Order>
}
