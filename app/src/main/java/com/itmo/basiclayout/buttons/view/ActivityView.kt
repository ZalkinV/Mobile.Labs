package com.itmo.basiclayout.buttons.view

import com.itmo.basiclayout.databinding.ActivityButtonsBinding

interface ActivityView {
    fun displayCoursePoints(coursePoints: String): ActivityButtonsBinding

}