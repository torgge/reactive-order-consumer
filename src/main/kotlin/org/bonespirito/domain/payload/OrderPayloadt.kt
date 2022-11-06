package org.bonespirito.domain.payload

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

interface MessagePayload

@Serializable
data class OrderPayload(
    val id: Long?,
    @Required
    val items: List<MaterialPayloadItem>?,
    val createdAt: String
) : MessagePayload

@Serializable
data class MaterialPayloadItem(
    val id: Long?,
    @Required
    val skuCode: String,
    @Required
    val quantity: Int,
    @Required
    val brand: String
) : MessagePayload
