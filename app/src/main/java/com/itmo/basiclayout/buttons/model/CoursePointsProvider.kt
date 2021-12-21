package com.itmo.basiclayout.buttons.model

interface CoursePointsProvider {
    fun get(): Int
    fun save(value: Int)
}
