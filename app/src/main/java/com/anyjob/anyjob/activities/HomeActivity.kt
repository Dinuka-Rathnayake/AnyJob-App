package com.anyjob.anyjob.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyjob.anyjob.R
import com.anyjob.anyjob.adapters.JobAdapter
import com.anyjob.anyjob.models.JobModel
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {

    //declare eliments
    private lateinit var btnNewJob: Button
    private lateinit var btnMyJob: Button

    private lateinit var allJobRecyclerView: RecyclerView
    private lateinit var jobList: ArrayList<JobModel>
    private lateinit var tvLoadingData: TextView

    private lateinit var dbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //initialize button
        btnNewJob = findViewById(R.id.btnNewJob)
        btnMyJob = findViewById(R.id.btnMyJobs)

        //initialize recycle view
        allJobRecyclerView = findViewById(R.id.recyclerViewHome)
        allJobRecyclerView.layoutManager = LinearLayoutManager(this)
        tvLoadingData = findViewById(R.id.tvLoading)

        //create arraylist
        jobList = arrayListOf<JobModel>()

        //set click
        btnNewJob.setOnClickListener{
//            val intent = Intent(this, JobInsertActivity::class.java)
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
        btnMyJob.setOnClickListener{
            val intent = Intent(this, MyJobsActivity::class.java)
            startActivity(intent)
        }

        getJobsData()

    }

    //recycleView
    private fun getJobsData(){
        allJobRecyclerView.visibility = View.GONE
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
                    allJobRecyclerView.adapter = mAdapter
                    //onclick
                    mAdapter.setOnItemClickListener(object: JobAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@HomeActivity, AllJobDetailsActivity::class.java)

                            //put extra
                            intent.putExtra("jobId", jobList[position].JobId)
                            intent.putExtra("jobTitle", jobList[position].JobTitle)
                            intent.putExtra("jobDescription", jobList[position].JobDescription)
                            intent.putExtra("jobBudget", jobList[position].JobBudget)
                            intent.putExtra("jobLocation", jobList[position].JobLocation)
                            startActivity(intent)
                        }
                    })

                    //change visibility
                    allJobRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }


}