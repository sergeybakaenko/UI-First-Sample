package com.formafit.architecture.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.formafit.architecture.databinding.ViewRepositoryItemBinding

class RepositoryAdapter(var repositories: List<RepositoriesProps.RepositoryProps>) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun getItemCount() = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewRepositoryItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repositories[position])

    class ViewHolder(val binding: ViewRepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoriesProps.RepositoryProps) {
            binding.props = item
            binding.executePendingBindings()
        }
    }
}


