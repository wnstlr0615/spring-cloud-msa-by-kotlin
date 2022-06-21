package com.example.catalogsservice.repository

import com.example.catalogsservice.entity.CatalogEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CatalogRepository : JpaRepository<CatalogEntity, Long> {
    fun findByProductId(productId: String): CatalogEntity?
}
