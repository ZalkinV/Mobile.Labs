package com.itmo.basiclayout.task3.company.domain

import com.itmo.basiclayout.task3.company.gateway.CompanyGateway

class CompanyInteractorImpl(
    private val companyGateway: CompanyGateway,
) : CompanyInteractor {

    override fun get(id: Int) = companyGateway.get(id)

}