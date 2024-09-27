package com.example.newproject180.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newproject180.databinding.ActivityProfileBinding

class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize binding
        binding = ActivityProfileBinding.inflate(layoutInflater)

        // Set the content view to the root of the binding
        setContentView(binding.root)

        // Setup back button functionality
        backMainPage()

        logoutPage()
        cartPage()
    }

    private fun cartPage() {
        binding.cartBtnLayout.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
    }

    private fun logoutPage() {
        binding.logoutLayout.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun backMainPage() {
        binding.mainActivitiy.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
