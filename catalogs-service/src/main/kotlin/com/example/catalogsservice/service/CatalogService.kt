package com.example.catalogsservice.service

import com.example.catalogsservice.dto.CatalogResponse

interface CatalogService {
    fun findAllCatalog(): List<CatalogResponse>
}
