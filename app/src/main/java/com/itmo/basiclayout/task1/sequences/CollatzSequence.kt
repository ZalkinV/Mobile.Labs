package com.itmo.basiclayout.task1.sequences

class CollatzSequence(value: Long): Sequence {
    var value = value
        private set

    override fun getNext(): Long {
        value = if (value % 2 == 0L)
            value / 2
        else
            value * 3 + 1

        return value
    }
}