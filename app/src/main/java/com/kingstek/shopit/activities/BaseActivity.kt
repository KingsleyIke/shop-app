package com.kingstek.shopit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kingstek.shopit.R

open class BaseActivity : AppCompatActivity() {

    fun showErrorSnackBar (message: String, errorMessage: Boolean) {
        val snackbar = Snackbar.make(findViewById(R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackbar.view

        if(errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarError)
            )
        } else {
            ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarSuccess)
        }
        snackbar.show()
    }
}