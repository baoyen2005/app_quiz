package com.example.app_quiz.utils

import android.graphics.Color
import com.example.app_quiz.R


/*
val: read-only (chỉ để đọc) chỉ khỏi tạo getter,
với var: mutable (có thể thay đổi được): khởi tạo getter và setter
 */
object RankIcon {
    val rankIcon =  arrayOf(
        R.drawable.smile,
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4,
        R.drawable.a5,
        R.drawable.a6,
        R.drawable.a7,
        R.drawable.a8,
        R.drawable.a9,
        R.drawable.a10,
        R.drawable.a11,
        R.drawable.a12,
        R.drawable.a13,
        R.drawable.a14,
        R.drawable.a15,
        R.drawable.a16,
        R.drawable.a17,

            )
    var currentRankIcon = 0
    fun getRank(): Int {
        currentRankIcon = (currentRankIcon+1)% rankIcon.size
        return rankIcon[currentRankIcon]
    }

}
