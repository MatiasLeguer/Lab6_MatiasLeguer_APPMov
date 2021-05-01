package com.example.miniproyecto2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText


class FragmentContactInfo : Fragment() {

    lateinit var communicate: ICommunicator
    private val viewModel: ContactInfoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact_info, container, false)

        communicate = activity as ICommunicator
        val save_btn = view.findViewById<Button>(R.id.save_btn)
        val name = view.findViewById<TextInputEditText>(R.id.TIET_name)
        val phone = view.findViewById<TextInputEditText>(R.id.TIET_phone)

        save_btn.setOnClickListener {
            val data = InfoContact(name =  name.editableText.toString(), phoneNumber = phone.editableText.toString())
            viewModel.createContact(data as InfoContact)
            viewModel.navigator.navigateToFirstFragment()

        }
        return view
    }


}