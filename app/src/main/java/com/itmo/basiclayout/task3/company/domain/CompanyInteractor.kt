package com.itmo.basiclayout.task3.company.domain

interface CompanyInteractor {

    suspend fun getCompany(id: Int): CompanyEntity

}