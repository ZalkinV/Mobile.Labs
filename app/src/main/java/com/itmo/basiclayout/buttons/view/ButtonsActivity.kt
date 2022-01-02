package com.itmo.basiclayout.buttons.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.widget.doOnTextChanged
import com.itmo.basiclayout.R
import com.itmo.basiclayout.buttons.dataAccess.CoursePointsProviderImpl
import com.itmo.basiclayout.buttons.presenter.ButtonsPresenter
import com.itmo.basiclayout.buttons.presenter.ButtonsPresenterImpl
import com.itmo.basiclayout.task1.Task1Activity
import com.itmo.basiclayout.databinding.ActivityButtonsBinding
import com.itmo.basiclayout.task2.Task2Activity
import com.itmo.basiclayout.task3.company.ui.Task3Activity

class ButtonsActivity : AppCompatActivity(), ActivityView {

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

    private lateinit var buttonsPresenter : ButtonsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityButtonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeDependencies()
        setListeners()
        buttonsPresenter.onInitialization()
        initializeDrawer()
    }

    private fun initializeDependencies() {
        buttonsPresenter = ButtonsPresenterImpl(this, CoursePointsProviderImpl(baseContext))
    }

    override fun onStop() {
        super.onStop()
        buttonsPresenter.saveCoursePoints()
    }

    override fun displayCoursePoints(coursePoints: String) = with(binding) {
        textViewCoursePoints.text = coursePoints
    }

    override fun changeCoursePointsColor(newColor: Int) = with(binding) {
        if (newColor != textViewCoursePoints.currentTextColor)
            textViewCoursePoints.setTextColor(newColor)
    }

    private fun setListeners() = binding.apply {
        buttonCoursePointsDecrease.setOnClickListener {
            buttonsPresenter.onDecreaseCoursePointsButtonClick()
        }

        buttonCoursePointsIncrease.setOnClickListener {
            buttonsPresenter.onIncreaseCoursePointsButtonClick()
        }

        textViewCoursePoints.doOnTextChanged { _, _, _, _ ->
            buttonsPresenter.onCoursePointsTextChanged()
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
                R.id.button_task3 -> openTask3Activity()
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

        val intent = Intent(this, Task2Activity::class.java)
        startActivity(intent)
    }

    private fun openTask3Activity() {
        Log.i(logTag, "Open Task3 activity")

        val intent = Intent(this, Task3Activity::class.java)
        startActivity(intent)
    }
}