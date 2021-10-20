package com.itmo.basiclayout.sequences

class NaturalSequence(value: Long): Sequence {
    var value = value
        private set

    override fun getNext(): Long {
        value += 1
        return value
    }
}