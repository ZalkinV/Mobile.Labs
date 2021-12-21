package com.itmo.basiclayout.buttons.presenter

import com.itmo.basiclayout.R
import com.itmo.basiclayout.buttons.CoursePointsConsts
import com.itmo.basiclayout.buttons.model.ButtonsPreferenceProviderImpl

class ButtonsPresenterImpl(
    private val preferenceProvider: ButtonsPreferenceProviderImpl
) : ButtonsPresenter {

    override var coursePoints: Int = getSavedCoursePoints()
        set(value) {
            field = when {
                value < CoursePointsConsts.MIN -> CoursePointsConsts.MIN
                value > CoursePointsConsts.MAX -> CoursePointsConsts.MAX
                else -> value
            }
        }

    override fun decreaseCoursePoints() =
        if (coursePoints > CoursePointsConsts.MIN)
            --coursePoints
        else
            coursePoints

    override fun increaseCoursePoints() =
        if (coursePoints < CoursePointsConsts.MAX)
            ++coursePoints
        else
            coursePoints

    override fun getColorForCoursePoints() = when {
        coursePoints < CoursePointsConsts.MIN_MARK_C -> R.color.red
        coursePoints < CoursePointsConsts.MIN_MARK_B -> R.color.orange
        coursePoints < CoursePointsConsts.MIN_MARK_A -> R.color.yellow
        else -> R.color.green
    }

    private fun getSavedCoursePoints() =
        preferenceProvider.getCoursePoints()

    fun saveCoursePoints() =
        preferenceProvider.saveCoursePoints(coursePoints)
}
