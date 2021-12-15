package com.itmo.basiclayout.task1.dataSources

import com.itmo.basiclayout.task1.ListItemDetails
import com.itmo.basiclayout.task1.keysEnums.IconEnum
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