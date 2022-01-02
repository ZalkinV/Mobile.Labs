package com.itmo.basiclayout.buttons.presenter

import androidx.core.content.res.ResourcesCompat
import com.itmo.basiclayout.R
import com.itmo.basiclayout.buttons.CoursePointsConsts
import com.itmo.basiclayout.buttons.dataAccess.CoursePointsProviderImpl
import com.itmo.basiclayout.buttons.view.ActivityView
import com.itmo.basiclayout.task1.Task1Activity
import com.itmo.basiclayout.task2.Task2Activity
import com.itmo.basiclayout.task3.company.ui.Task3Activity

class ButtonsPresenterImpl(
    private val view: ActivityView,
    private val coursePointsProvider: CoursePointsProviderImpl
) : ButtonsPresenter {

    override var coursePoints: Int = coursePointsProvider.get()

    override fun onInitialization() {
        view.displayCoursePoints(coursePoints.toString())
    }

    override fun onDecreaseCoursePointsButtonClick() {
        if (coursePoints > CoursePointsConsts.MIN)
            --coursePoints

        view.displayCoursePoints(coursePoints.toString())
    }

    override fun onIncreaseCoursePointsButtonClick() {
        if (coursePoints < CoursePointsConsts.MAX)
            ++coursePoints

        view.displayCoursePoints(coursePoints.toString())
    }

    override fun onCoursePointsTextChanged() {
        val color = getColorForCoursePoints()

        val newTextViewColor = ResourcesCompat.getColor(view.getResources(), color, null)
        view.changeCoursePointsColor(newTextViewColor)
    }


    private fun getColorForCoursePoints() = when {
        coursePoints < CoursePointsConsts.MIN_MARK_C -> R.color.red
        coursePoints < CoursePointsConsts.MIN_MARK_B -> R.color.orange
        coursePoints < CoursePointsConsts.MIN_MARK_A -> R.color.yellow
        else -> R.color.green
    }

    override fun saveCoursePoints() =
        coursePointsProvider.save(coursePoints)

    override fun onTask1ButtonClicked() {
        view.openActivity(Task1Activity::class.java)
    }

    override fun onTask2ButtonClicked() {
        view.openActivity(Task2Activity::class.java)
    }

    override fun onTask3ButtonClicked() {
        view.openActivity(Task3Activity::class.java)
    }
}
