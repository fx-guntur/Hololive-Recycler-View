package com.example.hololiverecyclerview.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hololiverecyclerview.DetailActivity
import com.example.hololiverecyclerview.R
import com.example.hololiverecyclerview.databinding.ItemRowHololiveBinding
import com.example.hololiverecyclerview.model.Member

class ListMemberAdapter(private val listMember: ArrayList<Member>) : RecyclerView.Adapter<ListMemberAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowHololiveBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemRowHololiveBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMember.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, region, description, photo) = listMember[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.binding.imgItemPhoto)
        holder.binding.tvItemRegion.text = region
        holder.binding.tvItemName.text = name
        holder.binding.tvItemDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMember[holder.adapterPosition])
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Member)
    }
}