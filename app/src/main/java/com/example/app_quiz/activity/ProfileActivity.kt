package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_quiz.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build

import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.app_quiz.models.Quiz
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sigup.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
//        val edtTen : EditText = findViewById(R.id.edtUsernamesignup)
//        val edtMSV : EditText = findViewById(R.id.edtIDSignUp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        txtEmail.text = firebaseAuth.currentUser?.email
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("user").document(user.uid).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    txtname.text = it.result.get("name").toString()
                    txtcode.text = it.result.get("student code").toString()

                }
            }

        profile_header_container.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

}