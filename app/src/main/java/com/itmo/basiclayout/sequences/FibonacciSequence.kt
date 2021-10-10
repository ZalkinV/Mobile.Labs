package com.itmo.basiclayout.sequences

class FibonacciSequence {
    private var prevValue = 0L
    private var value = 1L

    fun getNext(): Long {
        val valueToReturn = value

        val nextValue = value + prevValue
        prevValue = value
        value = nextValue

        return valueToReturn
    }
}