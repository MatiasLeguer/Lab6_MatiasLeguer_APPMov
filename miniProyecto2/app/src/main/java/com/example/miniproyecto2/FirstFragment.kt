package com.example.miniproyecto2

import android.icu.text.IDNA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: contactInfoRecyclerViewAdapter
    private val viewModel: ContactInfoViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(activity as MainActivity)
    }

    override fun onDestroy() {
        viewModel.writeText()
        super.onDestroy()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val btn_add = view.findViewById<Button>(R.id.btn_add_contact)

        adapter = contactInfoRecyclerViewAdapter()
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)


        viewModel.createdContact.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            viewModel.addContact(it)
        })
        viewModel.myContact.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.set(it)
        })



        btn_add.setOnClickListener {
            viewModel.navigator.navigateToAddContact()
        }
        return view
    }



}