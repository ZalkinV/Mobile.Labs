package com.itmo.basiclayout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class ButtonsActivity : AppCompatActivity() {

    companion object {
        private const val logTag = "BUTTONS"
    }

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)

        initializeDrawer()
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