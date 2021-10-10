package com.itmo.basiclayout.sequences

class NaturalSequence(startValue: Long) {
    private var value = startValue

    fun getNext(): Long {
        value += 1
        return value
    }
}