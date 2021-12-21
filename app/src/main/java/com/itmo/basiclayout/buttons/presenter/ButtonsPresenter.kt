package com.itmo.basiclayout.buttons.presenter

interface ButtonsPresenter {
    var coursePoints: Int
    fun decreaseCoursePoints(): Int
    fun increaseCoursePoints(): Int
    fun getColorForCoursePoints(): Int
    fun saveCoursePoints()
}
