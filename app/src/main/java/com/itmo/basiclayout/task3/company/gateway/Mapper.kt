package com.itmo.basiclayout.task3.company.gateway

import com.itmo.basiclayout.task3.company.domain.CompanyEntity

fun CompanyApiResponse.toEntity() = CompanyEntity(
    name,
    description,
    imgUrl,
)
