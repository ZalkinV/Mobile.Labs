package com.itmo.basiclayout

interface DataSource {
    fun fetchData(elementsCount: Int): List<ListItemDetails>
}