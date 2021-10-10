package com.itmo.basiclayout

import kotlin.random.Random

class DataSource {
    val names = listOf("Ann", "Bob", "Alice", "Maria", "Marting", "Carl")

    fun fetchData(elementsCount: Int): MutableList<ListItemDetails> {
        val listItems = mutableListOf<ListItemDetails>()

        val iconIndex = Random.nextInt(0, IconEnum.values().size)
        val icon = IconEnum.values()[iconIndex]

        for (i in 1..elementsCount) {
            listItems.add(ListItemDetails(
                "Title $i",
                "Descritpion $i",
                icon))
        }

        return listItems
    }
}