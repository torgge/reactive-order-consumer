package org.bonespirito.domain.service

import org.bonespirito.domain.model.Order

interface OrderFacade {
    fun process(vo: Order)
}
