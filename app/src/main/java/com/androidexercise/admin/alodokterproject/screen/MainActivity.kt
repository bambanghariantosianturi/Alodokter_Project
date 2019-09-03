package com.androidexercise.admin.alodokterproject.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.androidexercise.admin.alodokterproject.R
import com.androidexercise.admin.alodokterproject.fragment.HomeFragment
import com.androidexercise.admin.alodokterproject.fragment.ProfileFragment
import com.androidexercise.admin.alodokterproject.util.Config
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var toolbar: ActionBar? = null
    private var sharedPreferences: SharedPreferences? = null

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment()
                    toolbar?.title = "HOME"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, fragment.javaClass.simpleName).commit()
                    return@OnNavigationItemSelectedListener true

                }
                R.id.action_bookmark -> {
                    toolbar?.title = "PROFILE"
                    val fragment = ProfileFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, fragment.javaClass.simpleName).commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE)

        toolbar = supportActionBar
        val fragment = HomeFragment()
        toolbar?.title = "HOME"
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, fragment.javaClass.simpleName).commit()

        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        val inflaterMenu = menuInflater
        inflaterMenu.inflate(R.menu.sign_out_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_close -> {
                val editor = sharedPreferences?.edit()
                editor?.putBoolean(Config.LOGIN_SHARED_PREF, false)
                editor?.putBoolean(Config.REGISTER_SHARED_PREF, false)
                editor?.putString(Config.NAME_USER_SHARED, "")
                editor?.putString(Config.EMAIL_USER_SHARED, "")
                editor?.commit()

                val itm = Intent(this, LoginActivity::class.java)
                startActivity(itm)
                finish()}
        }
        return super.onOptionsItemSelected(item)
    }
}
