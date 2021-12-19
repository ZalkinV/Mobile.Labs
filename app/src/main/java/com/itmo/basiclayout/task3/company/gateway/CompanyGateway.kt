package com.itmo.basiclayout.task3.company.gateway

interface CompanyGateway {
    fun get(id: Int): CompanyApiModel
}