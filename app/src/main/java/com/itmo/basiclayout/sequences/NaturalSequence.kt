package com.itmo.basiclayout.sequences

class NaturalSequence(value: Long) {
    var value = value
        private set

    fun getNext(): Long {
        value += 1
        return value
    }
}