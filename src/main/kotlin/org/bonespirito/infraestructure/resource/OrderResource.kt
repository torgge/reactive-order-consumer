package org.bonespirito.infraestructure.resource

import io.smallrye.mutiny.Multi
import org.bonespirito.application.OrderService
import org.bonespirito.domain.converters.toPayload
import org.bonespirito.domain.converters.toVO
import org.bonespirito.domain.payload.OrderPayload
import java.time.Duration
import java.util.*
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class OrderResource(
    private val orderService: OrderService
) {

    @POST
    fun createOrder(payload: OrderPayload): Response {
        orderService.add(payload.toVO())
        return Response
            .status(Response.Status.CREATED)
            .entity(payload)
            .build()
    }

    @GET
    fun getOrders(): Multi<List<OrderPayload>> {
        return orderService.multiOrders().map { it.map { it.toPayload() } }
    }

    @GET
    @Path("/every")
    fun every(): Multi<List<Long?>> {
        return Multi.createFrom().ticks()
            .every(Duration.ofSeconds(10))
            .map { orderService.listOrders().map { it.toPayload().id } }
            .onOverflow()
            .drop()
    }
}
