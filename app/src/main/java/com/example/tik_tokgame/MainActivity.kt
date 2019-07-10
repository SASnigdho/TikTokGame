package com.example.tik_tokgame

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    fun onButtonClick(view: View)
    {
        val selectedButton: Button = view as Button
        var selectedButtonId = 0

        when(selectedButton.id)
        {
            button1_Id.id -> selectedButtonId = 1
            button2_Id.id -> selectedButtonId = 2
            button3_Id.id -> selectedButtonId = 3
            button4_Id.id -> selectedButtonId = 4
            button5_Id.id -> selectedButtonId = 5
            button6_Id.id -> selectedButtonId = 6
            button7_Id.id -> selectedButtonId = 7
            button8_Id.id -> selectedButtonId = 8
            button9_Id.id -> selectedButtonId = 9
        }
        //Toast.makeText(this, "Button Id: $selectedButtonId", Toast.LENGTH_SHORT).show()

        playGame(selectedButton, selectedButtonId)
    }

    var playerYou = ArrayList<Int>()
    var playerComputer = ArrayList<Int>()
    var activePlayer = 1

    fun playGame (selectedButton: Button, selectedButtonId: Int)
    {
        if (activePlayer == 1)
        {
            selectedButton.text = "X"
            selectedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen))
            playerYou.add(selectedButtonId)
            activePlayer = 2
            autoPlay()
        }
        else {
            selectedButton.text = "Y"
            selectedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorYollo))
            playerComputer.add(selectedButtonId)
            activePlayer = 1
        }
        selectedButton.isEnabled = false

        //Check who is Winner
        winner()
    }

    fun winner()
    {
        var winPlayer = -1

        //Row 1
        if (playerYou.contains(1) && playerYou.contains(2) && playerYou.contains(3)) { winPlayer = 1 }

        if (playerComputer.contains(1) && playerComputer.contains(2) && playerComputer.contains(3)) { winPlayer = 2 }

        //Row 2
        if (playerYou.contains(4) && playerYou.contains(5) && playerYou.contains(5)) { winPlayer = 1 }

        if (playerComputer.contains(4) && playerComputer.contains(5) && playerComputer.contains(6)) { winPlayer = 2 }

        //Row 3
        if (playerYou.contains(7) && playerYou.contains(8) && playerYou.contains(9)) { winPlayer = 1 }

        if (playerComputer.contains(7) && playerComputer.contains(8) && playerComputer.contains(9)) { winPlayer = 2 }

        //For Column

        //col 1
        if (playerYou.contains(1) && playerYou.contains(4) && playerYou.contains(7)) { winPlayer = 1 }

        if (playerComputer.contains(1) && playerComputer.contains(4) && playerComputer.contains(7)) { winPlayer = 2 }

        //Col 2
        if (playerYou.contains(2) && playerYou.contains(5) && playerYou.contains(8)) { winPlayer = 1 }

        if (playerComputer.contains(2) && playerComputer.contains(5) && playerComputer.contains(8)) { winPlayer = 2 }

        //Col 3
        if (playerYou.contains(3) && playerYou.contains(6) && playerYou.contains(9)) { winPlayer = 1 }

        if (playerComputer.contains(3) && playerComputer.contains(6) && playerComputer.contains(9)) { winPlayer = 2 }

        if (winPlayer != -1)
        {
            if (winPlayer == 1) { Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show() }
            else { Toast.makeText(this, "Computer Win!", Toast.LENGTH_SHORT).show() }
        }
    }

    //Computer Play
    fun autoPlay()
    {
        var unSelectedButtonId = ArrayList<Int>()

        for (cellId in 1..9) {
            if ( !( playerYou.contains(cellId) || playerComputer.contains(cellId) ) ) {
                unSelectedButtonId.add(cellId)
            }
        }

        val random = Random()
        val randomIndex = random.nextInt(unSelectedButtonId.size -0) + 0
        val cellId = unSelectedButtonId[randomIndex]

        var selectedButton: Button?

        when(cellId) {
            1 -> selectedButton = button1_Id
            2 -> selectedButton = button2_Id
            3 -> selectedButton = button3_Id
            4 -> selectedButton = button4_Id
            5 -> selectedButton = button5_Id
            6 -> selectedButton = button6_Id
            7 -> selectedButton = button7_Id
            8 -> selectedButton = button8_Id
            9 -> selectedButton = button9_Id

            else -> {
                selectedButton = button1_Id
            }
        }

        playGame(selectedButton, cellId)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as playerYou specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
