package com.example.app_quiz.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.app_quiz.R
import com.example.app_quiz.adapters.QuizAdapter
import com.example.app_quiz.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.*;

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {

    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var fireStore: FirebaseFirestore
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationView.setNavigationItemSelectedListener  (this)
        setUpView()

    }



    private fun setUpView() {
        setUpFireStore()
        setUpDrawerLayout()
        setUpRecycleView()
        //setUpDatePicker()

    }



    private fun setUpFireStore() {



        /*
        đây c ơi, chỗ này bị lỗi này, t k hiểu sao lỗi, debug nó báo null ở đây này
         */
        fireStore = FirebaseFirestore.getInstance() // firebase: null
        val collectionReference = fireStore.collection("quiz")

        collectionReference.addSnapshotListener { value, error ->

            if (value == null || error != null) {
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }

            Log.d("DATA", value.toObjects(Quiz::class.java).toString());
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java)) // vào đây thì debug ko chạy tiếp được nữa
            adapter.notifyDataSetChanged()

        }

        //nó sinh ra ntn nhỉ
        // hay là do mặc định của kotlin về cái đó
        // t k hiểu cái addSnapshotListenner b ạ// nó là lệnh mặc định r
       //oke tạm hieeir đoạn này là để lấy dữ liệu về đi
        //chả thấy chỗ nó đẩy lên đâu - bài này chưa xogn đâu bạn ạ :(

    }

    private fun setUpRecycleView() {
        adapter = QuizAdapter(this, quizList)
        quizRecycleView.layoutManager = GridLayoutManager(this, 1)
        quizRecycleView.adapter = adapter
    }

    private fun setUpDrawerLayout() {
        setSupportActionBar(appBar) // thanh công cụ
        actionBarDrawerToggle = ActionBarDrawerToggle(this,
                mainDrawer, R.string.app_name, R.string.app_name
        )
        actionBarDrawerToggle.syncState()
//       navigationView.setOnClickListener(){
//            val  intent = Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
////            mainDrawer.closeDrawers()
//
//        }
    }

    //chỉ định options menu cho một activity, chúng ta override phương thức
    // click vao icon ==> drawer menu
    //Phương thức này truyền menu item nào được chọn.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        firebaseAuth = FirebaseAuth.getInstance()
        when(item.itemId){
            R.id.btnOut -> {

                    FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginIntro::class.java)
                    startActivity(intent)


            }
            R.id.btnRateUs -> {
                
            }
        }
        mainDrawer.closeDrawers()
        return true
    }
}