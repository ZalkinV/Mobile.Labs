package com.itmo.basiclayout.buttons.model

interface ButtonsPreferenceProvider {
    fun getCoursePoints(): Int
    fun saveCoursePoints(value: Int)
}
