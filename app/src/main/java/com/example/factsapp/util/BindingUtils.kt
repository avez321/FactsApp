package com.example.factsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.factsapp.R
import com.example.factsapp.model.Row
import com.example.factsapp.ui.facts.FactsAdapter


object BindingUtils {
    @JvmStatic
    @BindingAdapter("android:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Glide
            .with(imageView.context)
            .load(url?.replace("http","https"))
            .placeholder(R.drawable.loading_wait_time)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_no_image_found)
            .centerCrop()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("android:factsData")
    fun setArticleData(recyclerView: RecyclerView, factsArrayList: List<Row>?) {
        factsArrayList?.let {
            val factsAdapter = recyclerView.adapter as FactsAdapter
            factsAdapter.setData(it as ArrayList<Row>)
        }
    }
 }



