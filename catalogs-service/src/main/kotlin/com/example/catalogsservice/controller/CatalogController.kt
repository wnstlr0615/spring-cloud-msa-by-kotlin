package com.example.catalogsservice.controller

import com.example.catalogsservice.dto.CatalogResponse
import com.example.catalogsservice.service.CatalogService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    val catalogService: CatalogService
) {
    @GetMapping("/health_check")
    fun status(request: HttpServletRequest): String {
        return "It`s Working in Catalog Service on Port ${request.serverPort}"
    }
    @GetMapping("/catalogs")
    fun findAllCatalog(): ResponseEntity<List<CatalogResponse>> {
        val catalogs = catalogService.findAllCatalog()
        return ResponseEntity.ok(catalogs)
    }
}