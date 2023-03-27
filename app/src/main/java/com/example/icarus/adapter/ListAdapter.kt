package com.example.icarus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.icarus.databinding.ListItemBinding
import com.example.icarus.dto.Member

class ListAdapter(private val members: MutableList<Member>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return members.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ListViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ListViewHolder).binding
        binding.itemDate.text = "gg"
    }




}