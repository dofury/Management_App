package com.example.icarus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.icarus.adapter.ListAdapter
import com.example.icarus.databinding.FragmentListBinding
import com.example.icarus.dialog.AddMemberDialog
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private var _mainActivity: MainActivity? = null
    private var _adapter: ListAdapter? = null
    private var members: MutableList<Member>? = mutableListOf()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        _mainActivity = activity as MainActivity?
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.fab.setOnClickListener { view ->

            val dialog = AddMemberDialog(_mainActivity!!,this,_adapter!!)
            dialog.show()
        }

    }

    fun init(){
        members = MyApplication.db.allMembers
        _adapter = ListAdapter(members!!,activity as MainActivity)
        binding.rcvList.adapter = _adapter
        binding.rcvList.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}