package com.kingstek.shopit.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.kingstek.shopit.R
import com.kingstek.shopit.utils.Constants
import com.kingstek.shopit.utils.GlideLoader
import kotlinx.android.synthetic.main.activity_add_product.*
import java.io.IOException

class AddProductActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        setupActionBar()

        iv_add_update_product.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setupActionBar() {

        setSupportActionBar(toolbar_add_product_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_add_product_activity.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {

                // The permission code is similar to the user profile image selection.
                R.id.iv_add_update_product -> {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Constants.showImageChooser(this@AddProductActivity)
                    } else {
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Constants.READ_STORAGE_PERMISSION_CODE)
                    }
                }

                R.id.btn_submit -> {
//                    if (validateProductDetails()) {
//
//                        uploadProductImage()
                    }
                }
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.READ_STORAGE_PERMISSION_CODE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Constants.showImageChooser(this@AddProductActivity)
            } else {
                Toast.makeText(this, resources.getString(R.string.read_storage_permission_denied), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.PICK_IMAGE_REQUEST_CODE && data!!.data != null) {

            // Replace the add icon with edit icon once the image is selected.
            iv_add_update_product.setImageDrawable(ContextCompat.getDrawable(this@AddProductActivity, R.drawable.ic_vector_edit))

            // The uri of selection image from phone storage.
           val  mSelectedImageFileUri = data.data!!

            try {
                // Load the product image in the ImageView.
                GlideLoader(this@AddProductActivity).loadUserPicture(mSelectedImageFileUri!!, iv_product_image)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}