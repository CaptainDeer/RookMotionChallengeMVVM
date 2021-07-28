package com.captaindeer.rookmotionchallengemvvm.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.captaindeer.rookmotionchallengemvvm.R
import com.captaindeer.rookmotionchallengemvvm.databinding.ActivityMainBinding
import com.captaindeer.rookmotionchallengemvvm.ui.login.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Nav Header
        val header = binding.navView.getHeaderView(0)
        val email = header.findViewById(R.id.tv_nav_mail) as TextView
        email!!.text = FirebaseAuth.getInstance().currentUser!!.email.toString()
        //NavBar
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.extra -> Toast.makeText(this, "Espacio extra", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "Configuracion", Toast.LENGTH_SHORT).show()
                R.id.logout -> logOut()
            }
            true
        }
    }

    override fun onBackPressed() {
        if (this.binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            this.binding.drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    private fun logOut() {
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }
}