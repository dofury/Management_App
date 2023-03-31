package com.example.icarus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.icarus.databinding.FragmentHomeBinding
import com.example.icarus.dialog.AddSincerityDialog
import com.example.icarus.dto.Member
import com.example.icarus.util.MyApplication
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            val contentResolver = (requireActivity() as MainActivity).contentResolver
            try {
                //contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                //Toast.makeText(mainActivity, "Selected file: $displayName", Toast.LENGTH_SHORT).show()
                contentResolver.openInputStream(uri)?.use {
                        inputStream ->
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val members: MutableList<Member> = mutableListOf()
                    var line: String? = reader.readLine()
                    var count = 0
                    while(line!=null){
                        val tokens = line.split(",")
                        if(count>0){
                            var member = Member()
                            member.uid = tokens[0].toInt() //uid
                            member.name = tokens[1] //name
                            member.studentId = tokens[2] //uid
                            member.age = tokens[3].toInt() //uid
                            member.grade = tokens[4]
                            member.gender = tokens[5] //uid
                            member.phoneNumber = tokens[6]
                            member.campus = tokens[7]
                            member.major = tokens[8] //uid
                            member.field = tokens[9]//uid
                            member.level = tokens[10] //uid
                            member.team = tokens[11]
                            members.add(member)
                        }
                        line = reader.readLine()
                        count++
                    }
                    MyApplication.db.allAddMember(members)
                }


                // Do something with the selected file
            } catch (e: SecurityException) {
                Toast.makeText(activity,"오류가 발생했습니다", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnList.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_ListFragment)
        }

        binding.btnLoad.setOnClickListener {
            getContent.launch("*/*");
        }

        binding.btnSincerity.setOnClickListener {
            val dialog = AddSincerityDialog(activity as MainActivity)
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}