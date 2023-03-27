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
import com.example.icarus.dto.Member

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val members: MutableList<Member> = mutableListOf()
        members.add(Member(0,"","","","","","",""))
        binding.rcvList.adapter = ListAdapter(members)
        binding.rcvList.addItemDecoration(DividerItemDecoration(context,LinearLayoutManager.VERTICAL))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createLayoutManager(): LinearLayoutManager {
        val manager = LinearLayoutManager(context)
        manager.reverseLayout = true
        manager.stackFromEnd = true
        return manager
    }
}