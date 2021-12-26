package com.itmo.basiclayout.buttons.presenter

interface ButtonsPresenter {
    var coursePoints: Int
    fun onDecreaseCoursePointsButtonClick()
    fun onIncreaseCoursePointsButtonClick()
    fun getColorForCoursePoints(): Int
    fun saveCoursePoints()
}
