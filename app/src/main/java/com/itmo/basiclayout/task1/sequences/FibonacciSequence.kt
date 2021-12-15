package com.itmo.basiclayout.task1.sequences

class FibonacciSequence(
    prevValue: Long,
    value: Long
): Sequence {
    var prevValue = prevValue
        private set
    var value = value
        private set

    override fun getNext(): Long {
        val valueToReturn = value

        val nextValue = value + prevValue
        prevValue = value
        value = nextValue

        return valueToReturn
    }
}