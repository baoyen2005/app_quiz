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
                    //1 c??i dialog


                    val auth: FirebaseAuth = FirebaseAuth.getInstance()
                    val user = auth.currentUser  // 2 d??ng n??y le ????? l???y UID c???a t??i kho???n v???a taoj th??nh c??ng


                    val map = hashMapOf(  // c??i n??y n?? gi???ng nh?? model // n?? ch??a th??ng tin c???n ?????y l??n
                        "name" to edtTen,
                        "student code" to edtMa,
                        "uid" to user.uid,
                    )


                    val firestone = FirebaseFirestore.getInstance() // d??ng n??y th?? hi???u r???i ????ng k
                    firestone.collection("user") // c??i n??y l?? t???o ra collection ????ng k?uk
                        .document(user.uid)// id n??y n?? t??? sithooiuk n?? l?? uid c???a t??i kho???n k th??? tr??ng vs ai ??????c
                        //n??n d??ng n?? sau l??c mu???n l???y ra c??ng d??? - ok hieur ch??t. gi?? t th??? c??i ch??? profile nh??
                        .set(map) // bt l??c l???y ch??? n??y l?? get ????ng k - ???// ?????y l??n v???i l???y xu???ng n?? kh??c nhau ch??? n??y th??i
                        .addOnCompleteListener { Log.d("TAG", "DocumentSnapshot successfully written!") }
                    // c??n ch??? n??y l?? th???c hi???n l???nh ?????y l??n

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