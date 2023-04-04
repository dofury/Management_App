package com.example.icarus

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.icarus.adapter.ListAdapter
import com.example.icarus.databinding.ActivitySearchBinding
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication

class SearchActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private var _adapter: ListAdapter? = null
    private var members: MutableList<Member> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)

        val query = intent.getStringExtra("query")

        members = MyApplication.db.getMemberName(query!!)

        init()

        setContentView(binding.root)


    }

    private fun init(){
        _adapter = ListAdapter(members,this)
        binding.recycleView.adapter = _adapter
        binding.recycleView.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )

    }
}
