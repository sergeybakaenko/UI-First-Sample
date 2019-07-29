package com.formafit.architecture.ui.repositories

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("isVisible")
fun setVisibleOrGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("ownerImage")
fun setOwnerImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .apply(RequestOptions.circleCropTransform())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@BindingAdapter("repositoriesData")
fun setRepositoriesData(recyclerView: RecyclerView, repositories: RepositoriesProps.Repositories) {
    if (repositories is RepositoriesProps.Repositories.Loaded) {
        val adapter = recyclerView.adapter
        if (adapter != null) {
            (adapter as RepositoryAdapter).apply {
                this.repositories = repositories.items
                notifyDataSetChanged()
            }
        } else {
            recyclerView.adapter = RepositoryAdapter(repositories.items)
        }
    }
}

