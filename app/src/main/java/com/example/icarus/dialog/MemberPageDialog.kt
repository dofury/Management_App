package com.example.icarus.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Adapter
import androidx.annotation.RequiresApi
import com.example.icarus.ListFragment
import com.example.icarus.MainActivity
import com.example.icarus.adapter.ListAdapter
import com.example.icarus.databinding.ActivityMainBinding
import com.example.icarus.databinding.DialogAddMemberBinding
import com.example.icarus.databinding.DialogMemberPageBinding
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication
import java.text.SimpleDateFormat

class MemberPageDialog(private val mainActivity: MainActivity,private val adapter: ListAdapter): Dialog(mainActivity) {
    private lateinit var binding: DialogMemberPageBinding

    fun show(members: MutableList<Member>, position: Int){
        var isDelete = false
        binding = DialogMemberPageBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.tvName.text = members[position].name
        binding.tvStudentId.text = members[position].studentId
        binding.tvGender.text = members[position].gender
        binding.tvAge.text = members[position].age.toString()
        binding.tvPhoneNumber.text = members[position].phoneNumber

        binding.tvMajor.text = members[position].major
        binding.tvField.text = members[position].field
        binding.tvLevel.text = members[position].level
        binding.tvTeam.text = members[position].team

        val sincerity = MyApplication.db.getSincerity(members[position].studentId)
        binding.tvEasyCount.text = sincerity.oneCount.toString()
        binding.tvNormalCount.text = sincerity.twoCount.toString()
        binding.tvTwiceCount.text = sincerity.threeCount.toString()
        binding.tvSumCount.text = sincerity.sumCount.toString()



        binding.ibClose.setOnClickListener(View.OnClickListener {
            dismiss()
        })
        binding.ibDelete.setOnClickListener(View.OnClickListener {
            MyApplication.db.deleteMember(members[position])

            members.removeAt(position)

            adapter.notifyItemRemoved(position)




            isDelete = true

            dismiss()

        })

        setOnDismissListener {
            if(!isDelete){//삭제된 로그가 아니라면 메모 업데이트
                //members[position].memo = binding.evMemo.text.toString()
                MyApplication.db.updateMember(members[position])
            }
            adapter.notifyItemChanged(position)
        }

        //크기 설정
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
        setCanceledOnTouchOutside(true)
        setCancelable(true)
        super.show()
    }
}