package com.example.icarus.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Adapter
import com.example.icarus.ListFragment
import com.example.icarus.MainActivity
import com.example.icarus.adapter.ListAdapter
import com.example.icarus.databinding.ActivityMainBinding
import com.example.icarus.databinding.DialogAddMemberBinding
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication

class AddMemberDialog(private val context: MainActivity, private val listFragment: ListFragment, private val adapter: ListAdapter): Dialog(context) {
    private lateinit var binding: DialogAddMemberBinding
    override fun show() {
        binding = DialogAddMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            val member = Member(
                0,
                binding.etStudentId.text.toString(),
                binding.etName.text.toString(),
                binding.etGender.text.toString(),
                binding.etAge.text.toString().toInt(),
                binding.etPhoneNumber.text.toString(),
                binding.etGrade.text.toString(),
                binding.etCampus.text.toString(),
                binding.etMajor.text.toString(),
                binding.etField.text.toString(),
                binding.etLevel.text.toString(),
                binding.etTeam.text.toString()
            )

            MyApplication.db.addMember(member)
            adapter.addMember(member)
            listFragment.init()
            dismiss()
        }
        binding.ibClose.setOnClickListener {
            dismiss()
        }
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        super.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}