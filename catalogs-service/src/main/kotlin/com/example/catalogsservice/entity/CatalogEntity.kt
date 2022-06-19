package com.example.catalogsservice.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "catalog")
class CatalogEntity(
    @Column(nullable = false, unique = true, length = 120)
    val productId: String,
    @Column(nullable = false)
    var productName: String,
    @Column(nullable = false)
    var stock: Int,
    @Column(nullable = false)
    var unitPrice: Int,
    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
