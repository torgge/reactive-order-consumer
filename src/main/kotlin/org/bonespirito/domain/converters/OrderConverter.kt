package org.bonespirito.domain.converters

import org.bonespirito.domain.model.Material
import org.bonespirito.domain.model.Order
import org.bonespirito.domain.payload.MaterialPayloadItem
import org.bonespirito.domain.payload.OrderPayload
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

fun OrderPayload.toVO() = Order(
    id = this.id,
    items = this.items!!.map { it.toVO(this.id) },
    createdAt = LocalDateTime.parse(this.createdAt)
)

fun MaterialPayloadItem.toVO(orderId: Long?) = Material(
    id = this.id,
    orderId = orderId,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand
)

fun Order.toPayload() = OrderPayload(
    id = this.id,
    items = this.items.map { it.toPayload() },
    createdAt = this.createdAt.format(ISO_LOCAL_DATE_TIME)
)

fun Material.toPayload() = MaterialPayloadItem(
    id = this.id,
    skuCode = this.skuCode,
    quantity = this.quantity,
    brand = this.brand
)
