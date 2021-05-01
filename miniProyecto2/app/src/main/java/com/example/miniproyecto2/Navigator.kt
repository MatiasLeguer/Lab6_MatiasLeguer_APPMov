package com.example.miniproyecto2

import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.setFragmentResultListener

class Navigator(val activity: MainActivity?) {

    fun navigateToAddContact(){
        val contactInfoFragment = FragmentContactInfo()
        activity?.supportFragmentManager?.commit {
            this.replace(R.id.fragment_container, contactInfoFragment)
            this.addToBackStack(null)
        }
    }

    fun navigateToFirstFragment(){
        val firstFragment = FirstFragment()
        activity?.supportFragmentManager?.commit {
            this.replace(R.id.fragment_container, firstFragment)
        }

    }
}