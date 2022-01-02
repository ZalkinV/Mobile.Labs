package com.itmo.basiclayout.buttons.view

import android.app.Activity
import android.content.res.Resources

interface ActivityView {
    fun displayCoursePoints(coursePoints: String)
    fun changeCoursePointsColor(newColor: Int)

    fun openActivity(activityClass: Class<out Activity>)

    fun getResources(): Resources
}