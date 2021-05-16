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
import kotlinx.android.synthetic.main.activity_rate_star.*


class RankingActivity : AppCompatActivity() {

    var userList = mutableListOf<User>()
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseUser = firebaseAuth.currentUser
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("score")
            .get().addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        userList?.add(document.toObject(User::class.java))
                    }

                }
                userList.sortByDescending { it.yourScore }
                billView()
                // sx theo diem tang dan

                // sx giam
              //  userList.sortByDescending { it.yourScore }

            }

        btEnd.setOnClickListener{
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


