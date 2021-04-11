package com.example.factsapp.ui.facts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.factsapp.R
import com.example.factsapp.databinding.ItemFactsBinding
import com.example.factsapp.model.Row


class FactsAdapter : RecyclerView.Adapter<FactsAdapter.ContentViewHolder>() {
    private var factsArrayList: ArrayList<Row>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val factsBinding = DataBindingUtil.inflate<ItemFactsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_facts,
            parent,
            false
        )

        return ContentViewHolder(factsBinding)
    }

    override fun getItemCount(): Int = factsArrayList?.size ?: 0


    override fun onBindViewHolder(contentViewHolder: ContentViewHolder, position: Int) {
        contentViewHolder.bind(factsArrayList?.get(position))
    }


    fun setData(factsArrayList: ArrayList<Row>) {
        this.factsArrayList = factsArrayList
        notifyDataSetChanged()
    }

    class ContentViewHolder(
        val binding: ItemFactsBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(row: Row?) {
            binding.row = row
        }
    }

}