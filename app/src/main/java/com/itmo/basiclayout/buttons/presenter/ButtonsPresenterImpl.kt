package com.itmo.basiclayout.buttons.presenter

import com.itmo.basiclayout.R
import com.itmo.basiclayout.buttons.CoursePointsConsts
import com.itmo.basiclayout.buttons.dataAccess.CoursePointsProviderImpl

class ButtonsPresenterImpl(
    private val coursePointsProvider: CoursePointsProviderImpl
) : ButtonsPresenter {

    override var coursePoints: Int = coursePointsProvider.get()
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

    override fun saveCoursePoints() =
        coursePointsProvider.save(coursePoints)
}
