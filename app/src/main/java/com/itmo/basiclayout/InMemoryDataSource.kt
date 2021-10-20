package com.itmo.basiclayout

import kotlin.random.Random

class InMemoryDataSource: DataSource {
    override fun fetchData(elementsCount: Int): List<ListItemDetails> {
        val listItems = List(elementsCount) {
            val iconIndex = Random.nextInt(0, IconEnum.values().size)
            val icon = IconEnum.values()[iconIndex]

            ListItemDetails(
                "Title $it",
                "Description $it",
                icon)
        }

        return listItems
    }
}