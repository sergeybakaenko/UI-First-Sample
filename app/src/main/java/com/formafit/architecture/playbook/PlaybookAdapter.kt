package com.forma.fit.ui.screens.playbook

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.formafit.architecture.R
import kotlinx.android.synthetic.main.view_playbook_item.view.*

class PlaybookAdapter(private val context: Context, private val items: List<String>, val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.view_playbook_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.playbookItem.text = items[position]
        holder.itemView.setOnClickListener {
            clickListener(position)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val playbookItem: TextView = view.playbookItem
}
