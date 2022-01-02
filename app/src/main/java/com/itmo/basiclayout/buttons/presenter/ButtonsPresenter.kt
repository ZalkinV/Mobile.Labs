package com.itmo.basiclayout.buttons.presenter

interface ButtonsPresenter {
    val coursePoints: Int
    fun onInitialization()
    fun onDecreaseCoursePointsButtonClick()
    fun onIncreaseCoursePointsButtonClick()
    fun onCoursePointsTextChanged()
    fun saveCoursePoints()
    fun onTask1ButtonClicked()
    fun onTask2ButtonClicked()
    fun onTask3ButtonClicked()
}
