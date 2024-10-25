package com.example.samsara.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

fun Fragment.firebaseAuthWithGoogle(idToken:String,mAuth: FirebaseAuth){
    val credential = GoogleAuthProvider.getCredential(idToken, null)
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(context,"signInWithCredential:success", Toast.LENGTH_LONG).show()
                //updateUI(currentuser)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(context,"signInWithCredential:failure"+task.exception, Toast.LENGTH_LONG).show()
                //updateUI(null)
            }
        }
}