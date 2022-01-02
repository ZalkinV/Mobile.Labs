package com.itmo.basiclayout.buttons.presenter

interface ButtonsPresenter {
    val coursePoints: Int
    fun onDecreaseCoursePointsButtonClick()
    fun onIncreaseCoursePointsButtonClick()
    fun onCoursePointsTextChanged()
    fun saveCoursePoints()
    fun onInitialization()
}
