package com.itmo.basiclayout.task3.company.gateway

import com.itmo.basiclayout.task3.company.domain.CompanyEntity

interface CompanyGateway {
    suspend fun get(id: Int): CompanyEntity
}
