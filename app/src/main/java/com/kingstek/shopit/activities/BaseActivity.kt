package com.kingstek.shopit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.kingstek.shopit.R

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun showErrorSnackBar (message: String, errorMessage: Boolean) {
        val snackbar = Snackbar.make(findViewById(R.id.content))
    }
}