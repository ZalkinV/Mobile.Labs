package com.itmo.basiclayout.task1.sequences

class NaturalSequence(value: Long): Sequence {
    var value = value
        private set

    override fun getNext(): Long {
        value += 1
        return value
    }
}