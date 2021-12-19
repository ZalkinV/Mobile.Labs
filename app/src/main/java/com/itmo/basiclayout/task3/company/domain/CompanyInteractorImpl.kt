package com.itmo.basiclayout.task3.company.domain

import com.itmo.basiclayout.task3.company.gateway.CompanyGateway
import com.itmo.basiclayout.task3.company.gateway.CompanyGatewayImpl

class CompanyInteractorImpl(
    private val companyGateway: CompanyGateway = CompanyGatewayImpl(),
) : CompanyInteractor {

    override fun get(id: Int) = companyGateway.get(id)

}