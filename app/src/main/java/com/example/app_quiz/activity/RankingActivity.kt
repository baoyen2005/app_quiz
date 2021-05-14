package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_quiz.R
import com.example.app_quiz.adapters.RankingAdapter
import com.example.app_quiz.models.Quiz
import com.example.app_quiz.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_ranking.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RankingActivity : AppCompatActivity() {

    var userList: MutableList<User>? = null
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseUser = firebaseAuth.currentUser

        firestore = FirebaseFirestore.getInstance()
        var name: String = ""
        var score: String = ""

        //khoi tao kieu j ta ^^


        firestore.collection("user")
            .get().addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        userList?.add(document.toObject(User::class.java))
                    }

                }
                billView()

            }

       linearRank.setOnClickListener{
           val intent = Intent(this, EndActivity::class.java)
           startActivity(intent)
           finish()
       }
    }

    private fun billView() {

        val adapter = RankingAdapter(this, userList!!)
        recycleViewRanking.layoutManager = LinearLayoutManager(this)
        recycleViewRanking.setHasFixedSize(true)
        recycleViewRanking.adapter = adapter
        adapter.notifyDataSetChanged()
    }

}


