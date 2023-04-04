package com.example.icarus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.icarus.ListFragment
import com.example.icarus.MainActivity
import com.example.icarus.databinding.ListItemBinding
import com.example.icarus.dialog.MemberPageDialog
import com.example.icarus.dto.Member

class ListAdapter(private val members: MutableList<Member>,private val activity: AppCompatActivity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return members.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ListViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as ListViewHolder).binding

        binding.itemStudentId.text = members[position].studentId
        binding.itemName.text = members[position].name
        binding.itemLevel.text = members[position].level
        binding.itemMajor.text = members[position].major

        buttonEvent(holder,position)

    }

    private fun buttonEvent(holder: ListViewHolder, position: Int){
        holder.itemView.setOnClickListener(View.OnClickListener {
            val dialog = MemberPageDialog(activity,this)
            dialog.show(members,position)
        })
    }

    fun addMember(member: Member) {
        members.add(member)
        notifyItemInserted(members.size - 1)
    }




}