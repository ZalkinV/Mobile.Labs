package com.itmo.basiclayout.dataSources

import com.itmo.basiclayout.ListItemDetails

interface DataSource {
    fun fetchData(elementsCount: Int): List<ListItemDetails>
}