package com.itmo.basiclayout.sequences

class CollatzSequence(startValue: Long) {
    private var value = startValue

    fun getNext(): Long {
        value = if (value % 2 == 0L)
            value / 2
        else
            value * 3 + 1

        return value
    }
}