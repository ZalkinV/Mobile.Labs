package com.itmo.basiclayout.task3.company.domain

interface CompanyInteractor {

    suspend fun get(id: Int): CompanyEntity

}