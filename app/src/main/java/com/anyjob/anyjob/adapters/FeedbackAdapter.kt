package com.anyjob.anyjob.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anyjob.anyjob.R
import com.anyjob.anyjob.models.FeedbackModel

class FeedbackAdapter(private val feedbackList: ArrayList<FeedbackModel>):
    RecyclerView.Adapter<FeedbackAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.feedback_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = feedbackList[position]
        holder.tvFeedbackName.text = currentEmp.cusFeedback
    }

    override fun getItemCount(): Int {
        return feedbackList.size
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val tvFeedbackName : TextView = itemView.findViewById(R.id.tvFeedbackName)
    }

}