package com.example.catalogsservice.service

import com.example.catalogsservice.dto.CatalogResponse
import com.example.catalogsservice.entity.CatalogEntity
import com.example.catalogsservice.repository.CatalogRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct

@Service
@Transactional(readOnly = true)
class CatalogServiceImpl(
    val catalogRepository: CatalogRepository
) : CatalogService {
    @PostConstruct
    fun init(){
        catalogRepository.save(CatalogEntity("CATALOG-0001","Berlin", 100, 1500))
        catalogRepository.save(CatalogEntity("CATALOG-0002","Tokyo", 100, 900))
        catalogRepository.save(CatalogEntity("CATALOG-0003","Stockholm", 100, 1200))
    }

    override fun findAllCatalog(): List<CatalogResponse> {
        return catalogRepository.findAll().map(::CatalogResponse)
    }
}