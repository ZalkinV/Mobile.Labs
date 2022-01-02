package com.itmo.basiclayout.buttons.view

import android.content.res.Resources

interface ActivityView {
    fun displayCoursePoints(coursePoints: String)
    fun changeCoursePointsColor(newColor: Int)

    fun getResources(): Resources
}