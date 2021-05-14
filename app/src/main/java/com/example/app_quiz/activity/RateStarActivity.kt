package com.example.app_quiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.Toast
import com.example.app_quiz.R
import kotlinx.android.synthetic.main.activity_rate_star.*

class RateStarActivity : AppCompatActivity(), RatingBar.OnRatingBarChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_star)
        ratingBar.onRatingBarChangeListener = this
        btnSend.setOnClickListener{
           Toast.makeText(this,"I give it! Thankiu you for rate us!", Toast.LENGTH_SHORT).show()
           val intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
       }
    }
    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {

        textView.text = "$p1"
    }
}