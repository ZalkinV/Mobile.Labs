package com.itmo.basiclayout.buttons.dataAccess

interface CoursePointsProvider {
    fun get(): Int
    fun save(value: Int)
}
