package com.itmo.basiclayout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.doOnTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ButtonsActivity : AppCompatActivity() {

    companion object {
        private const val logTag = "BUTTONS"
    }

    private lateinit var button_decrease: ImageButton
    private lateinit var button_increase: ImageButton
    private lateinit var textView_points: TextView

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    private var coursePoints: Int = 0
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        initializePreferences()

        findComponents()
        setListeners()
        initializeComponents()

        initializeDrawer()
    }

    override fun onStop() {
        super.onStop()

        preferences.edit(commit = true) {
            putInt(PreferencesEnum.Buttons.COURSE_POINTS.name, coursePoints)
        }
    }

    private fun initializePreferences() {
        preferences = getSharedPreferences(PreferencesEnum.Buttons.PREF_NAME.name, Context.MODE_PRIVATE)
        coursePoints = preferences.getInt(PreferencesEnum.Buttons.COURSE_POINTS.name, 0)
    }

    private fun findComponents() {
        button_decrease = findViewById(R.id.button_coursePoints_decrease)
        button_increase = findViewById(R.id.button_coursePoints_increase)
        textView_points = findViewById(R.id.textView_coursePoints)
    }

    private fun initializeComponents() {
        textView_points.text = coursePoints.toString()
    }

    private fun setListeners() {
        button_decrease.setOnClickListener {
            if (coursePoints > 0)
                textView_points.text = (--coursePoints).toString()
        }

        button_increase.setOnClickListener {
            if (coursePoints < 999)
                textView_points.text = (++coursePoints).toString()
        }

        textView_points.doOnTextChanged { _, _, _, _ ->
            val color = when {
                coursePoints < 50 -> R.color.red
                coursePoints < 60 -> R.color.orange
                coursePoints < 80 -> R.color.yellow
                else -> R.color.green
            }

            val newTextViewColor = ResourcesCompat.getColor(resources, color, null)
            if (newTextViewColor != textView_points.currentTextColor)
                textView_points.setTextColor(newTextViewColor)
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
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)


        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.button_task1 -> openTask1Activity()
                R.id.button_task2 -> openTask2Activity()
                else ->
                {
                    Toast.makeText(this, "Not implemented, yet!", Toast.LENGTH_SHORT).show()
                    return@setNavigationItemSelectedListener false
                }
            }

            drawerLayout.closeDrawers()
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