package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_quiz.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sigup.*


class SigupActivity : AppCompatActivity() {
    // ddanwg xuat va dang nhap google trong android voi fire base
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigup)
        firebaseAuth = FirebaseAuth.getInstance()
//        btnSigUp1.setOnClickListener{
//            this.sigUpUser()
//        }
        btnSignUp.setOnClickListener{
            sigUpUser()
        }
        txtSIgnIn.setOnClickListener{
            val intent  = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun sigUpUser(){
        val email: String = edtEmailSignUp!!.text.toString()
        val password : String = etPasswordSignUp!!.text.toString()
        val confirmPassword : String = etCofirmPassword!!.text.toString()

        if(email.isBlank()|| password.isBlank()||confirmPassword.isBlank()){
            Toast.makeText(this,"Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if(password != confirmPassword){
            Toast.makeText(this,"Password and Confirm password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Error creating user.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}