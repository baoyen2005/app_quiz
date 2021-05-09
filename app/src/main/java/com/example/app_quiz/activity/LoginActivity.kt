package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_quiz.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    /*
    Lateinit là khởi tạo sau.
    bạn không thể cung cấp một trình khởi tạo none-null trong constructor,
    nhưng bạn vẫn muốn tránh kiểm tra null khi gọi thuộc tính bên trong class

     muốn thuộc tính của bạn được khởi tạo từ bên ngoài theo cách có thể chưa biết trước
     */
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        btnLogin.setOnClickListener{
            login()
        }
        txtSignUp.setOnClickListener{
            val intent  = Intent(this, SigupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun login(){
        val email :String = edtEmailAddress.text.toString()
        val password : String = etPassword.text.toString()
        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this, "Email/password cannot be empty",Toast.LENGTH_SHORT).show()
            return
        }
        // dang nhap tai khoan
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                }
                else{
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()

                }
            }
    }
}