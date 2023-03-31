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
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            val dialog = AddMemberDialog(_mainActivity!!,this,_adapter!!)
            dialog.show()
/*            val member = Member(0,"","","","","","","","","")
            MyApplication.db.addMember(member)*/
        }

    }

    fun init(){
        members = MyApplication.db.allMembers
        _adapter = ListAdapter(members!!,activity as MainActivity,this)
        binding.rcvList.adapter = _adapter
        binding.rcvList.layoutManager = createLayoutManager()
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