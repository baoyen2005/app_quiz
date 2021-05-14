package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.app_quiz.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sigup.*


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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
        // t moi them 2 cai nay
        val edtTen =  edtUsernamesignup!!.text.toString()
        val edtMa =  edtIDSignUp?.text.toString()
        if(email.isBlank()|| password.isBlank()||confirmPassword.isBlank() || edtTen.isBlank() || edtMa.isBlank()){
            Toast.makeText(this,"Email or password or userName or student code can't be blank", Toast.LENGTH_SHORT).show()
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
                    //1 cái dialog


                    val auth: FirebaseAuth = FirebaseAuth.getInstance()
                    val user = auth.currentUser  // 2 dòng này le để lấy UID của tài khoản vừa taoj thành công


                    val map = hashMapOf(  // cái này nó giống như model // nó chưa thông tin cần đẩy lên
                        "name" to edtTen,
                        "student code" to edtMa,
                        "uid" to user.uid,
                    )


                    val firestone = FirebaseFirestore.getInstance() // dòng này thì hiểu rồi đúng k
                    firestone.collection("user") // cái này là tạo ra collection đúng k?uk
                        .document(user.uid)// id này nó tự sithooiuk nó là uid của tài khoản k thể trùng vs ai đươc
                        //nên dùng nó sau lúc muốn lấy ra cũng dễ - ok hieur chút. giơ t thử cái chỗ profile nhá
                        .set(map) // bt lúc lấy chỗ này là get đúng k - ừ// đẩy lên với lấy xuống nó khác nhau chỗ này thôi
                        .addOnCompleteListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                    // còn chỗ này là thực hiện lệnh đẩy lên

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