package com.kingstek.shopit.firestore

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.kingstek.shopit.activities.RegisterActivity
import com.kingstek.shopit.models.User

class FirestoreClass {

    private val mFirestore = FirebaseFirestore.getInstance()

    fun registerUser (activity: RegisterActivity, userInfo: User) {

        mFirestore.collection("users")
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener{ e ->
                activity.hideProgressDialog()
                Log.e( activity.javaClass.simpleName, "Error while registering the user", e)
            }
    }
}