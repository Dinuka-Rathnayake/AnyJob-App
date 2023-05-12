package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyjob.anyjob.R
import com.anyjob.anyjob.adapters.JobAdapter
import com.anyjob.anyjob.models.JobModel
import com.google.firebase.database.*

class MyJobsActivity : AppCompatActivity() {
    private lateinit var myJobRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var jobList: ArrayList<JobModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_jobs)

        //initialize recycle view
        myJobRecyclerView = findViewById(R.id.RecycleViewMyJos)
        myJobRecyclerView.layoutManager = LinearLayoutManager(this)

        //initialize text
        myJobRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        //create arraylist
        jobList = arrayListOf<JobModel>()

        getJobsData()
    }

    private fun getJobsData(){
        myJobRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        //firebase path
        dbRef = FirebaseDatabase.getInstance().getReference("Jobs")

        //add db data to ArrayList
        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                jobList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(JobModel::class.java)
                        jobList.add(empData!!)
                    }
                    val mAdapter = JobAdapter(jobList)
                    myJobRecyclerView.adapter = mAdapter
                        //onclick
                    mAdapter.setOnItemClickListener(object:JobAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@MyJobsActivity, JobDetailsActivity::class.java)

                            //put extra
                            intent.putExtra("jobId", jobList[position].JobId)
                            intent.putExtra("jobTitle", jobList[position].JobTitle)
                            intent.putExtra("jobDescription", jobList[position].JobDescription)
                            intent.putExtra("jobBudget", jobList[position].JobBudget)
                            startActivity(intent)
                        }
                    })

                    //change visibility
                    myJobRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}