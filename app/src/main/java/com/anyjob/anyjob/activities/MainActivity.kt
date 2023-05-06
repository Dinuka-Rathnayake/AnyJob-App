package com.anyjob.anyjob.activities

import SevAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anyjob.anyjob.R
import com.anyjob.anyjob.activities.models.ServiceMdl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var sevRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var sevList: ArrayList<ServiceMdl>
    private lateinit var dbRef: DatabaseReference

    private lateinit var btnReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sevRecyclerView = findViewById(R.id.rvSev)
        sevRecyclerView.layoutManager = LinearLayoutManager(this)
        sevRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        sevList = arrayListOf<ServiceMdl>()

        getEmployeesData()

        btnReg = findViewById(R.id.btnReg)

        btnReg.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun getEmployeesData() {

        sevRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Services")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sevList.clear()
                if (snapshot.exists()){
                    for (sevSnap in snapshot.children){
                        val sevData = sevSnap.getValue(ServiceMdl::class.java)
                        sevList.add(sevData!!)
                    }
                    val mAdapter = SevAdapter(sevList)
                    sevRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : SevAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@MainActivity, ServiceDetailsActivity::class.java)

                            //put extras
                            intent.putExtra("sevId", sevList[position].sevId)
                            intent.putExtra("sevType", sevList[position].sevType)
                            intent.putExtra("sevName", sevList[position].sevName)
                            intent.putExtra("sevDes", sevList[position].sevDes)
                            startActivity(intent)
                        }
//
                    })

                    sevRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}