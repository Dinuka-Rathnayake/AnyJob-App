package com.anyjob.anyjob.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyjob.anyjob.R
import com.anyjob.anyjob.adapters.FeedbackAdapter
import com.anyjob.anyjob.models.FeedbackModel
import com.google.firebase.database.*

class MyFeedbacksActivity : AppCompatActivity() {
    private lateinit var feedbackRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var feedbackList: ArrayList<FeedbackModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_feedbacks)

        feedbackRecyclerView = findViewById(R.id.rvEmp)
        feedbackRecyclerView.layoutManager = LinearLayoutManager(this)
        feedbackRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        feedbackList = arrayListOf<FeedbackModel>()

        getEmployeesData()
    }



    private fun getEmployeesData() {
        feedbackRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Feedbacks")

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                feedbackList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(FeedbackModel::class.java)
                        feedbackList.add(empData!!)
                    }
                    val mAdapter = FeedbackAdapter(feedbackList)
                    feedbackRecyclerView.adapter = mAdapter

                    feedbackRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}