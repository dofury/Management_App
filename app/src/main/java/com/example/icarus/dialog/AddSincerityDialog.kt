package com.example.icarus.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Adapter
import com.example.icarus.MainActivity
import com.example.icarus.adapter.ListAdapter
import com.example.icarus.databinding.ActivityMainBinding
import com.example.icarus.databinding.DialogAddMemberBinding
import com.example.icarus.databinding.DialogAddSincerityBinding
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication

class AddSincerityDialog(private val context: MainActivity): Dialog(context) {
    private lateinit var binding: DialogAddSincerityBinding
    override fun show() {
        binding = DialogAddSincerityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnYes.setOnClickListener {

            val map = mutableMapOf<String, Int>()
            val str = binding.etContent.text

            val parseStr1 = str.replace("\\n".toRegex(), "")
            val parseStr2 = parseStr1.replace("\\s+".toRegex(), "")

            val regex = "([가-힣]+)(\\d+)".toRegex()

            regex.findAll(parseStr2).forEach {
                val name = it.groupValues[1]
                val key = it.groupValues[2].toInt()
                map[name] = key


                var sincerity = MyApplication.db.getSincerityName(name)
                when(key){
                    1 -> {
                        sincerity.oneCount += 1
                    }
                    2 -> {
                        sincerity.twoCount += 1
                    }
                    3 -> {
                        sincerity.threeCount += 1
                    }
                }
                sincerity.sumCount = sincerity.oneCount + sincerity.twoCount + sincerity.threeCount

                MyApplication.db.updateSincerity(sincerity)
            }



// 결과: {홍길동=1, 보고=2, 도훈=3}



            dismiss()
        }
        binding.btnNo.setOnClickListener {
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