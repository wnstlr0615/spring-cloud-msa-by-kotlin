package com.example.catalogsservice.dto

import com.example.catalogsservice.entity.CatalogEntity
import java.time.LocalDateTime

data class CatalogResponse(
    val productId: String,
    val productName: String,
    val stock: Int,
    val unitPrice: Int,
    val createdAt: LocalDateTime
) {
    constructor(catalog: CatalogEntity) : this(
        productId = catalog.productId,
        productName = catalog.productName,
        stock = catalog.stock,
        unitPrice = catalog.unitPrice,
        createdAt = catalog.createdAt
    )
}
