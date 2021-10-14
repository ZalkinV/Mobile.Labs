package com.itmo.basiclayout.sequences

class CollatzSequence(value: Long) {
    var value = value
        private set

    fun getNext(): Long {
        value = if (value % 2 == 0L)
            value / 2
        else
            value * 3 + 1

        return value
    }
}