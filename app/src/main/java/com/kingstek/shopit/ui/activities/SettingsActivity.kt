package com.kingstek.shopit.ui.activities

import android.os.Bundle
import android.view.View
import com.kingstek.shopit.R
import com.kingstek.shopit.firestore.FirestoreClass
import com.kingstek.shopit.models.User
import com.kingstek.shopit.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity(), View.OnClickListener{

    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R .layout.activity_settings)

        setupActionBar()
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_settings_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_settings_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    private fun getUserDetails() {

        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getUserDetails(this@SettingsActivity)
    }

    fun userDetailsSuccess(user: User) {

        mUserDetails = user

        hideProgressDialog()

        GlideLoader(this@SettingsActivity).loadUserPicture(user.image, iv_user_photo)

        tv_name.text = "${user.firstName} ${user.lastName}"
        tv_gender.text = user.gender
        tv_email.text = user.email
        tv_mobile_number.text = "${user.mobile}"
    }

    override fun onResume() {
        super.onResume()
        getUserDetails()
    }
}