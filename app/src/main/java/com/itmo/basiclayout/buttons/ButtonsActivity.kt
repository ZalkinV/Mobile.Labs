package com.itmo.basiclayout.buttons

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import com.itmo.basiclayout.task1.DetailsActivity
import com.itmo.basiclayout.R
import com.itmo.basiclayout.task1.Task1Activity
import com.itmo.basiclayout.databinding.ActivityButtonsBinding

class ButtonsActivity : AppCompatActivity() {

    companion object {
        private const val logTag = "BUTTONS"
    }

    /* How to configure view binding
    https://dev.to/henryudorji/using-view-binding-to-replace-findviewbyid-on-android-b5n
    https://medium.com/androiddevelopers/use-view-binding-to-replace-findviewbyid-c83942471fc
    https://developer.android.com/topic/libraries/view-binding#kts
     */
    private lateinit var binding: ActivityButtonsBinding

    private lateinit var drawerToggle: ActionBarDrawerToggle

    private lateinit var preferences: SharedPreferences
    private var coursePoints: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializePreferences()

        setListeners()
        initializeComponents()

        initializeDrawer()
    }

    override fun onStop() {
        super.onStop()

        preferences.edit(commit = true) {
            putInt(PreferenceConsts.COURSE_POINTS, coursePoints)
        }
    }

    private fun initializePreferences() {
        preferences = getSharedPreferences(PreferenceConsts.FILE_NAME, Context.MODE_PRIVATE)
        coursePoints = preferences.getInt(PreferenceConsts.COURSE_POINTS, 0)
    }

    private fun initializeComponents() = binding.apply {
        textViewCoursePoints.text = coursePoints.toString()
    }

    private fun setListeners() = binding.apply {
        buttonCoursePointsDecrease.setOnClickListener {
            if (coursePoints > Consts.MIN_COURSE_POINTS)
                textViewCoursePoints.text = (--coursePoints).toString()
        }

        buttonCoursePointsIncrease.setOnClickListener {
            if (coursePoints < Consts.MAX_COURSE_POINTS)
                textViewCoursePoints.text = (++coursePoints).toString()
        }

        textViewCoursePoints.doOnTextChanged { _, _, _, _ ->
            val color = when {
                coursePoints < Consts.MIN_COURSE_POINTS_MARK_C -> R.color.red
                coursePoints < Consts.MIN_COURSE_POINTS_MARK_B -> R.color.orange
                coursePoints < Consts.MIN_COURSE_POINTS_MARK_A -> R.color.yellow
                else -> R.color.green
            }

            val newTextViewColor = ResourcesCompat.getColor(resources, color, null)
            if (newTextViewColor != textViewCoursePoints.currentTextColor)
                textViewCoursePoints.setTextColor(newTextViewColor)
        }
    }

    /* How to make drawer:
    https://www.youtube.com/watch?v=zQh-QGGKPw0
    https://intensecoder.com/android-navigation-drawer-in-kotlin/
    https://medium.com/@ezichukwuamarachi/using-the-android-navigation-drawer-in-kotlin-6cf2cdd0e42f
    https://android--code.blogspot.com/2018/03/android-kotlin-navigation-drawer-example.html
    http://developer.alexanderklimov.ru/android/navigation_drawer_activity.php
     */
    private fun initializeDrawer() {
        drawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        )
        binding.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.button_task1 -> openTask1Activity()
                R.id.button_task2 -> openTask2Activity()
                else ->
                {
                    Toast.makeText(this, "Not implemented, yet!", Toast.LENGTH_SHORT).show()
                    return@setNavigationItemSelectedListener false
                }
            }

            binding.drawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    private fun openTask1Activity() {
        Log.i(logTag, "Open Task1 activity")

        val intent = Intent(this, Task1Activity::class.java)
        startActivity(intent)
    }

    private fun openTask2Activity() {
        Log.i(logTag, "Open Task2 activity")

        val intent = Intent(this, DetailsActivity::class.java)
        startActivity(intent)
    }
}