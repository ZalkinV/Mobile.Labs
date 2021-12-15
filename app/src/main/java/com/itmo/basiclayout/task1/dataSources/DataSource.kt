package com.itmo.basiclayout.task1.dataSources

import com.itmo.basiclayout.task1.ListItemDetails

interface DataSource {
    fun fetchData(elementsCount: Int): List<ListItemDetails>
}