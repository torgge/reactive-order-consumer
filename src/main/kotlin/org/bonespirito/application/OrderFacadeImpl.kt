package org.bonespirito.application

import org.bonespirito.domain.model.Order
import org.bonespirito.domain.service.OrderFacade
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class OrderFacadeImpl(
    private val orderService: OrderService
) : OrderFacade {

    override fun process(vo: Order) {
        orderService.add(vo)
    }
}
