package com.kingstek.shopit.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kingstek.shopit.R
import com.kingstek.shopit.models.User
import com.kingstek.shopit.utils.Constants
import kotlinx.android.synthetic.main.activity_register.*

class UserProfileActivity : AppCompatActivity() {

    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        if(intent.hasExtra(Constants.EXTRA_USER_DETAILS)) {

            mUserDetails = intent.getParcelableExtra(Constants.EXTRA_USER_DETAILS)!!
        }

        et_first_name.isEnabled = false
        et_first_name.setText(mUserDetails.firstName)

        et_last_name.isEnabled = false
        et_last_name.setText(mUserDetails.lastName)

        et_email.isEnabled = false
        et_email.setText(mUserDetails.email)

    }
}