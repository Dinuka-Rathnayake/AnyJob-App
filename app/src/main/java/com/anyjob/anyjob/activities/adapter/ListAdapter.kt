package com.anyjob.anyjob.activities.adapter

import android.app.Activity
import android.graphics.LinearGradient
import android.graphics.Movie
import android.opengl.Visibility
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.anyjob.anyjob.R
import com.anyjob.anyjob.activities.model.CommentModel

/**
 * @author chalana
 * @created 2023/05/06 | 5:42 PM
 * @contact Chalana.n@fidenz.com | 071 6 359 376
 */
class MyListAdapter (private val user:String,private val context: Activity, private val list: ArrayList<CommentModel>,
                     private var onItemEdit: ((comment: CommentModel) -> Unit ),private var onItemDelete: ((comment: CommentModel) -> Unit ))
    : ArrayAdapter<CommentModel>(context, R.layout.listview_item, list) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_item, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val imageView = rowView.findViewById(R.id.icon) as ImageView
        val edit = rowView.findViewById(R.id.img_edit) as ImageView
        val delete = rowView.findViewById(R.id.img_delete) as ImageView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val options = rowView.findViewById(R.id.ll_options) as LinearLayout

        options.isVisible = user.equals(list[position].user)

        edit.setOnClickListener{
            onItemEdit(list[position])
        }

        delete.setOnClickListener{
            onItemDelete(list[position])
        }

        titleText.text = list[position].user
        imageView.setImageResource(R.drawable.baseline_person_24)
        subtitleText.text = list[position].comment

        return rowView
    }


}