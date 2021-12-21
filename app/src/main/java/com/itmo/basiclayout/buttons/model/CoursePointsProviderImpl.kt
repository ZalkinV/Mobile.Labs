package com.itmo.basiclayout.buttons.model

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.itmo.basiclayout.buttons.PreferenceConsts

class CoursePointsProviderImpl(
    context: Context,
) : CoursePointsProvider {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PreferenceConsts.FILE_NAME, Context.MODE_PRIVATE)

    override fun get() =
        sharedPreferences.getInt(PreferenceConsts.COURSE_POINTS, 0)

    override fun save(value: Int) =
        sharedPreferences.edit {
            putInt(PreferenceConsts.COURSE_POINTS, value)
            apply()
        }
}
