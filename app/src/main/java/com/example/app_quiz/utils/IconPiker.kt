package com.example.app_quiz.utils

import android.graphics.Color
import com.example.app_quiz.R


/*
val: read-only (chỉ để đọc) chỉ khỏi tạo getter,
với var: mutable (có thể thay đổi được): khởi tạo getter và setter
 */
object IconPiker {
    val icons =  arrayOf(
            R.drawable.icon_1,
            R.drawable.icon_2,
            R.drawable.icon_3,
            R.drawable.icon_4,
            R.drawable.icon_6,
            R.drawable.icon_7,
            R.drawable.icon_8,
            R.drawable.icon_9,
            R.drawable.icon_10
            )
    var currentIcon =  0
    fun getIcon():Int{
        currentIcon= (currentIcon+1)% icons.size
        return icons[currentIcon]
    }

}
